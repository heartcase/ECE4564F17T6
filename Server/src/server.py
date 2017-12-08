from flask import Flask
from flask_restful import Resource, Api
from flask import request
from pymongo import *
from flask_cors import CORS, cross_origin
from flask_httpauth import HTTPBasicAuth
from flask import g
from functools import wraps
auth = HTTPBasicAuth()

client = MongoClient("localhost/ui", 8080)
database_name = "ECE4564_Final_Project"
collection_name = 'user'
collection_name2 = 'parking'
db = client[database_name]
collection = db[collection_name]
collection2 = db[collection_name2]


@auth.get_password
def get_password(username):
    g.user = username;
    return "123"   #correct password is 123

def check_available(string, list): # the method for parameter checking
    for each in list:
        if string == each:
            return True
    return False

class JSONEncoder(json.JSONEncoder):
    def default(self, o):
        if isinstance(o, ObjectId):
            return str(o)
        return json.JSONEncoder.default(self, o)

class LOGIN(Resource):
    decorators = [auth.login_required]  #need auth
    def get(self):
        uid = request.args.get('uid')
        if not uid.isnumeric():
            return {'Error':'Bad Parameters'}, 400


        # get functions for login here
        # login into server
        print("login get")
        print("the user name is:" + g.user)
        print("uid:" + uid)


        return {'Success': 'OK'}, 200

class LIST(Resource):

    def get(self):
        range = request.args.get('range')
        if not check_available(range, ["All", "Available", "Taken"]):
            return {'Error':'Bad Parameters'}, 400


        #get functions for here
		list_1 = collection2.find()
		list_2 = []
		for each in list_1:
			list_2.append(JSONEncoder().encode(each))
        #get the list of parking spots
        print("list get")
        print("range:" + range)



        spot1 = {"full": True, "id": 0, "location": "location", "name": "name"}   #return examples
        spot2 = {"full": False, "id": 1, "location": "location", "name": "name"}  #return examples
        #list = [spot1, spot2]  #return examples
        return {'list':list_2}, 200

class PARKING(Resource):
    decorators = [auth.login_required]

    def get(self,id):
        if not id.isnumeric():
            return {'Error':'Bad Parameters'}, 400


        #get functions here
		if collection.find_one({'id':id}):
			spot = collection.find_one({'id':id})['content']
        #check the status of the parking spot
        print("parking get")
        print("id:" + id)


        #spot = {"full": True, "id": 0, "location": "location", "name": "name"} #return examples
        return spot, 200


    def post(self,id):
        op = request.form.get('op')
        uid = request.form.get('uid')
        time = request.form.get('time')
        if not check_available(op, ["check_in", "check_out"]) or \
                not id.isnumeric() or \
                        not time.isnumeric() or \
                            int(time) not in range(1, 25) or \
                                not uid.isnumeric():
            return {'Error':'Bad Parameters'}, 400

        # post functions here
        # park or leave the parking spot
        print("the user name is:" + g.user)
        print("parking post")
        print("id:" + id)
        print("op:" + op)
        print("uid:" + uid)
        print("time:" + time)


        return {'Success': 'OK'}, 200

class USER(Resource):
    decorators = [auth.login_required]
    def get(self,uid):
        if not uid.isnumeric():
            return {'Error':'Bad Parameters'}, 400

        # get functions here
		if collection.find_one({'uid':uid}):
			content = collection.find_one({'uid':uid})['content']
        # check the status of user
			print("the user name is:" + g.user)
			print("User get")
			print("uid:" + uid)


			user = {"name": "Kevin","parking": "parking", "spot_id": 6,"uid": 0}
			return user, 200
		return {'content':None}, 200



def flaskService():
    app = Flask(__name__)
    CORS(app)
    api = Api(app)
    api.add_resource(LOGIN, '/Login')
    api.add_resource(LIST, '/ParkingSpots')
    api.add_resource(PARKING, '/ParkingSpots/<id>')
    api.add_resource(USER, '/User/<uid>')
    app.run(host='0.0.0.0')

if __name__ == '__main__':
	#test
	
    flaskService()
