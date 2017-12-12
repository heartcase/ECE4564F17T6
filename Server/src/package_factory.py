from mongo_datebase import *
from datetime import datetime


def pack_parking_spot(spot_id, name, location, status, uid):
    return{
        '_id': spot_id,
        'name': name,
        'status': status,
        'location': location,
        'uid': uid
    }


def pack_user(uid, username, spot_id, status, park_time, park_hour, password):
    return{
        '_id': uid,
        'username': username,
        'password': password,
        'spot_id': spot_id,
        'status': status,
    }


def get_counters():
    if not collection3.find_one({'name': 'counter'}):
        collection3.insert_one({'name': 'counter', 'uid': 1, 'spot_id': 1})
    counters = collection3.find_one({'name': 'counter'})
    return counters['uid'], counters['spot_id']


def new_spot():
    a, c = get_counters()
    spot = pack_parking_spot(c, 'Spot' + str(c), 'check_out', 'somewhere', 0)
    collection2.insert_one(spot)
    collection3.find_one_and_update({'name': 'counter'}, {'$set': {'spot_id': c + 1}})


def new_user():
    a, c = get_counters()
    user = pack_user(a, 'User' + str(a), -1, 'check_out', '', 0, '321')
    collection1.insert_one(user)
    collection3.find_one_and_update({'name': 'counter'}, {'$set': {'uid': a + 1}})


def pack_current_time():
    return datetime.now().strftime('%Y-%m-%d %H:%M:%S')