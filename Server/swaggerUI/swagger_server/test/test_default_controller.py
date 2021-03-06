# coding: utf-8

from __future__ import absolute_import

from flask import json
from six import BytesIO

from swagger_server.models.parking_spot import ParkingSpot  # noqa: E501
from swagger_server.models.user import User  # noqa: E501
from swagger_server.test import BaseTestCase


class TestDefaultController(BaseTestCase):
    """DefaultController integration test stubs"""

    def test_login_get(self):
        """Test case for login_get

        Login into server
        """
        response = self.client.open(
            '//Login',
            method='GET')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_parking_spots_get(self):
        """Test case for parking_spots_get

        get the list of the parking spots
        """
        query_string = [('range', 'range_example')]
        response = self.client.open(
            '//ParkingSpots',
            method='GET',
            query_string=query_string)
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_parking_spots_id_get(self):
        """Test case for parking_spots_id_get

        check the status of the parking spot
        """
        response = self.client.open(
            '//ParkingSpots/{id}'.format(id=56),
            method='GET')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_parking_spots_id_post(self):
        """Test case for parking_spots_id_post

        park or leave the parking spot
        """
        data = dict(operation='operation_example',
                    park_hour=56)
        response = self.client.open(
            '//ParkingSpots/{id}'.format(id=56),
            method='POST',
            data=data,
            content_type='multipart/form-data')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_user_uid_get(self):
        """Test case for user_uid_get

        check the status of the user
        """
        response = self.client.open(
            '//User/{uid}'.format(uid=56),
            method='GET')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))


if __name__ == '__main__':
    import unittest
    unittest.main()
