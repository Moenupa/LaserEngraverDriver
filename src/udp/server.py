import socket
import time

server = socket.socket(socket.AF_INET, socket.SOCK_DGRAM, socket.IPPROTO_UDP)

server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR,  1)
server.setsockopt(socket.SOL_SOCKET, socket.SO_BROADCAST, 1)

server.settimeout(0.2)
message = b"https://prod.liveshare.vsengsaas.visualstudio.com/join?7B27FB0C5BED0CE61A42C0C683E9CE54ED09"
while True:
    server.sendto(message, ('<broadcast>', 37020))
    print(f"UDP {message}")
    time.sleep(1)
