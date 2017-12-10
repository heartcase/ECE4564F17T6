import threading
from flask_server import *


if __name__ == '__main__':
    server_thread = threading.Thread(target=start_server)
    server_thread.start()