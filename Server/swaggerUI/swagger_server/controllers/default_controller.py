import connexion
import six

from swagger_server.models.parking_spot import ParkingSpot  # noqa: E501
from swagger_server.models.user import User  # noqa: E501
from swagger_server import util


def login_get():  # noqa: E501
    """Login into server

     # noqa: E501


    :rtype: User
    """
    return 'do some magic!'


def parking_spots_get(range=None):  # noqa: E501
    """get the list of the parking spots

     # noqa: E501

    :param range: show the list of parking spots by filter “all”/“available”
    :type range: str

    :rtype: List[ParkingSpot]
    """
    return 'do some magic!'


def parking_spots_id_get(id):  # noqa: E501
    """check the status of the parking spot

     # noqa: E501

    :param id: parking spot id
    :type id: int

    :rtype: ParkingSpot
    """
    return 'do some magic!'


def parking_spots_id_post(id, operation, park_hour=None):  # noqa: E501
    """park or leave the parking spot

    the user id # noqa: E501

    :param id: parking spot id
    :type id: int
    :param operation: the operation the user want to act [\&quot;check_in\&quot;, \&quot;check_out\&quot;]
    :type operation: str
    :param park_hour: the num of hour allow user to park [1-24]
    :type park_hour: int

    :rtype: None
    """
    return 'do some magic!'


def user_uid_get(uid):  # noqa: E501
    """check the status of the user

     # noqa: E501

    :param uid: user id
    :type uid: int

    :rtype: User
    """
    return 'do some magic!'
