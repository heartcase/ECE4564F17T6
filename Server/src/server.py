from flask import Flask
from flask_restful import Resource, Api
from flask import request
from pymongo import *

class LOGIN(Resource):
    def get(self):
        uid = request.args.get('uid')
        print("login get")
        print("uid:"+uid)
        #get functions for login here
        #login into server

        return {}

class LIST(Resource):
    def get(self):
        range = request.args.get('range')
        print("list get")
        print("range:" + range)

        #get functions for here
        #get the list of parking spots

        return {}

class PARKING(Resource):
    def get(self,id):
        print("parking get")
        print("id:"+id)
        #get functions here
        #check the status of the parking spot

        return {}
    def post(self,id):
        op = request.form.get('op')
        uid = request.form.get('uid')
        time = request.form.get('time')
        print("parking post")
        print("id:" + id)
        print("op:" + op)
        print("uid:" + uid)
        print("time:" + time)
        # post functions here
        #park or leave the parking spot

        return {}

class USER(Resource):
    def get(self,uid):
        print("User get")
        print("uid:" + uid)
        #get functions here
        #check the status of user

        return {}

def flaskService():
    app = Flask(__name__)
    api = Api(app)
    api.add_resource(LOGIN, '/Login')
    api.add_resource(LIST, '/ParkingSpots')
    api.add_resource(PARKING, '/ParkingSpots/<id>')
    api.add_resource(USER, '/User/<uid>')
    app.run(host='0.0.0.0')

if __name__ == '__main__':
    flaskService()