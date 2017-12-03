from flask import Flask
from flask_restful import Resource, Api
from flask import request

class LOGIN(Resource):
    def get(self):
        print("login get")
        #get functions for login here
        #login into server

        return {}

class LIST(Resource):
    def get(self):
        print("list get")
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
        print("parking post")
        print("id:" + id)
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