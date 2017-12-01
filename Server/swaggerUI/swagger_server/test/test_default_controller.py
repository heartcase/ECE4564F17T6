# coding: utf-8

from __future__ import absolute_import

from flask import json
from six import BytesIO

from swagger_server.models.parking_spot import ParkingSpot  # noqa: E501
from swagger_server.test import BaseTestCase


class TestDefaultController(BaseTestCase):
    """DefaultController integration test stubs"""

    def test_login_get(self):
        """Test case for login_get

        Login into server
        """
        query_string = [('uid', 56)]
        response = self.client.open(
            '//Login',
            method='GET',
            query_string=query_string)
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_parking_spots_get(self):
        """Test case for parking_spots_get

        
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

        
        """
        response = self.client.open(
            '//ParkingSpots/{id}'.format(id=56),
            method='GET')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_parking_spots_id_post(self):
        """Test case for parking_spots_id_post

        
        """
        query_string = [('op', 'op_example'),
                        ('uid', 56)]
        response = self.client.open(
            '//ParkingSpots/{id}'.format(id=56),
            method='POST',
            query_string=query_string)
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))


if __name__ == '__main__':
    import unittest
    unittest.main()
