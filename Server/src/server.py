from flask import Flask
from flask_restful import Resource, Api
from flask import request

class LOGIN(Resource):
    def get(self):
        #get functions here
        return {}

class LIST(Resource):
    def get(self):
        #get functions here
        return {}

class PARKING(Resource):
    def get(self,id):
        #get functions here
        return {}
    def post(self,id):
        # post functions here
        return {}

class USER(Resource):
    def get(self,uid):
        #get functions here
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