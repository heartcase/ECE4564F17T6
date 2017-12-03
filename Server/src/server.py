from flask import Flask
from flask_restful import Resource, Api
from flask import request

class PARKING(Resource):
    def get(self):
        #get functions here
        return {}
    def post(self):
        # post functions here
        return {}

def flaskService():
    app = Flask(__name__)
    api = Api(app)
    api.add_resource(PARKING, '/parking')
    app.run(host='0.0.0.0')

if __name__ == '__main__':

    flaskService()