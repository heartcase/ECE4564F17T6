# coding: utf-8

from __future__ import absolute_import
from datetime import date, datetime  # noqa: F401

from typing import List, Dict  # noqa: F401

from swagger_server.models.base_model_ import Model
from swagger_server import util


class User(Model):
    """NOTE: This class is auto generated by the swagger code generator program.

    Do not edit the class manually.
    """

    def __init__(self, uid: int=None, name: str=None, parking: str=None, spot_id: int=None):  # noqa: E501
        """User - a model defined in Swagger

        :param uid: The uid of this User.  # noqa: E501
        :type uid: int
        :param name: The name of this User.  # noqa: E501
        :type name: str
        :param parking: The parking of this User.  # noqa: E501
        :type parking: str
        :param spot_id: The spot_id of this User.  # noqa: E501
        :type spot_id: int
        """
        self.swagger_types = {
            'uid': int,
            'name': str,
            'parking': str,
            'spot_id': int
        }

        self.attribute_map = {
            'uid': 'uid',
            'name': 'name',
            'parking': 'parking',
            'spot_id': 'spot_id'
        }

        self._uid = uid
        self._name = name
        self._parking = parking
        self._spot_id = spot_id

    @classmethod
    def from_dict(cls, dikt) -> 'User':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The User of this User.  # noqa: E501
        :rtype: User
        """
        return util.deserialize_model(dikt, cls)

    @property
    def uid(self) -> int:
        """Gets the uid of this User.

        id of the user  # noqa: E501

        :return: The uid of this User.
        :rtype: int
        """
        return self._uid

    @uid.setter
    def uid(self, uid: int):
        """Sets the uid of this User.

        id of the user  # noqa: E501

        :param uid: The uid of this User.
        :type uid: int
        """

        self._uid = uid

    @property
    def name(self) -> str:
        """Gets the name of this User.

        name of the user  # noqa: E501

        :return: The name of this User.
        :rtype: str
        """
        return self._name

    @name.setter
    def name(self, name: str):
        """Sets the name of this User.

        name of the user  # noqa: E501

        :param name: The name of this User.
        :type name: str
        """

        self._name = name

    @property
    def parking(self) -> str:
        """Gets the parking of this User.

        if the user is parking  # noqa: E501

        :return: The parking of this User.
        :rtype: str
        """
        return self._parking

    @parking.setter
    def parking(self, parking: str):
        """Sets the parking of this User.

        if the user is parking  # noqa: E501

        :param parking: The parking of this User.
        :type parking: str
        """

        self._parking = parking

    @property
    def spot_id(self) -> int:
        """Gets the spot_id of this User.

        the id of parking spot  # noqa: E501

        :return: The spot_id of this User.
        :rtype: int
        """
        return self._spot_id

    @spot_id.setter
    def spot_id(self, spot_id: int):
        """Sets the spot_id of this User.

        the id of parking spot  # noqa: E501

        :param spot_id: The spot_id of this User.
        :type spot_id: int
        """

        self._spot_id = spot_id