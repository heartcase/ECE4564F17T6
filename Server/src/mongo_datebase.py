from pymongo import *
database_name = 'ECE4564'
collection_name1 = 'service_auth'
collection_name2 = 'parking_spot'
collection_name3 = 'database_server'
client = MongoClient()
db = client[database_name]
collection1 = db[collection_name1]
collection2 = db[collection_name2]
collection3 = db[collection_name3]