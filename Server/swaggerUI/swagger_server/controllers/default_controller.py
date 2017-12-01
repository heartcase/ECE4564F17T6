import connexion
import six

from swagger_server.models.parking_spot import ParkingSpot  # noqa: E501
from swagger_server import util


def login_get(uid):  # noqa: E501
    """Login into server

    check user crediential # noqa: E501

    :param uid: the user id
    :type uid: int

    :rtype: str
    """
    return 'do some magic!'


def parking_spots_get(range=None):  # noqa: E501
    """parking_spots_get

     # noqa: E501

    :param range: show the list of parking spots by filter [\&quot;All\&quot;, \&quot;Available\&quot;, \&quot;Taken\&quot;]
    :type range: str

    :rtype: List[ParkingSpot]
    """
    return 'do some magic!'


def parking_spots_id_get(id):  # noqa: E501
    """parking_spots_id_get

     # noqa: E501

    :param id: parking spot id
    :type id: int

    :rtype: ParkingSpot
    """
    return 'do some magic!'


def parking_spots_id_post(id, op, uid):  # noqa: E501
    """parking_spots_id_post

    the user id # noqa: E501

    :param id: parking spot id
    :type id: int
    :param op: the operation the user want to act [\&quot;check_in\&quot;, \&quot;check_out\&quot;]
    :type op: str
    :param uid: 
    :type uid: int

    :rtype: None
    """
    return 'do some magic!'
