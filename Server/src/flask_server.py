from package_factory import *
from flask import Flask
from flask_restful import Resource, Api
from flask import request
from flask_httpauth import HTTPBasicAuth

auth = HTTPBasicAuth()


def start_server():
    app = Flask(__name__)
    api = Api(app)
    api.add_resource(ParkingSpot, '/ParkingSpots/<int:spot_id>')
    api.add_resource(ParkingSpots, '/ParkingSpots')
    api.add_resource(User, '/Users/<int:uid>')
    api.add_resource(Login,'/Login')
    app.run(host='0.0.0.0')


class Login(Resource):
    method_decorators = [auth.login_required]

    def get(self):
        username = auth.username()
        user = collection1.find_one({"username": username})
        if user:
            return user


class ParkingSpot(Resource):
    method_decorators = {'post': [auth.login_required]}

    def get(self, spot_id):
        spot = collection2.find_one({'_id': spot_id})
        if spot:
            spot['uid'] = -1
            return spot

    def post(self, spot_id):
        operation = request.form.get('operation')
        park_hour = request.form.get('park_hour')
        user = collection1.find_one({"username": auth.username()})
        spot = collection2.find_one({'_id': spot_id})
        if operation == 'check_in':
            if spot:
                collection2.find_one_and_update({'_id': spot_id}, {'$set': {'status': 'check_in', 'uid': user['_id']}})
                collection1.find_one_and_update({"username": auth.username()},
                                                {'$set':
                                                {'status': 'check_in',
                                                 'spot_id': spot_id,
                                                 'park_time': pack_current_time(),
                                                 'park_hour': park_hour
                                                 }})
                return collection2.find_one({'_id': spot_id})
        elif operation == 'check_out':
            if spot:
                collection2.find_one_and_update({'_id': spot_id}, {'$set': {'status': 'check_out', 'uid': 0}})
                collection1.find_one_and_update({"username": auth.username()},
                                                {'$set':
                                                {'status': 'check_out',
                                                 'spot_id': 0,
                                                 'park_time': ''
                                                 }})
                return collection2.find_one({'_id': spot_id})


class User(Resource):
    method_decorators = [auth.login_required]

    def get(self, uid):
        user = collection1.find_one({"_id": uid})
        if user:
            return user


class ParkingSpots(Resource):

    def get(self):
        f = request.args.get('filter')
        if f == 'all':
            if collection2.find_one():
                spot_list = list(collection2.find())
                for each in spot_list:
                    each['uid'] = -1
                return spot_list
        elif f == 'available':
            if collection2.find_one({'status': 'check_out'}):
                spot_list = list(collection2.find({'status': 'check_out'}))
                for each in spot_list:
                    each['uid'] = -1
                return spot_list


@auth.get_password
def get_password(username):
    user = collection1.find_one({"username": username})
    if user:
        return user['password']
