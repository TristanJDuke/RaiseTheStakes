import socket
import csv
import mysql.connector




def check(key):
	
	with open('keys.txt') as f:
		keys = f.read().splitlines()
	if key in keys:
		return "Success"
	else:
		return "Fail"



def run():
	serverSocket = socket.socket(socket.AF_INET,socket.SOCK_STREAM)

	addr = ('localhost', 25000)
	buffsize = 1024

	serverSocket.bind(addr)
	serverSocket.listen(100) # Listen for up to 3 connections
	
	while True:
		print("Server is waiting for the connection......\n")
		clientSocket, address = serverSocket.accept()
		print("Accepted connection from ", address)
		key = clientSocket.recv(buffsize).decode('UTF-8')[2:]
		result = check(key)
		print(key)
		clientSocket.send(len(result).to_bytes(2, byteorder='big'))
		clientSocket.send(result.encode('UTF-8'))

def connect():
	try:
		mydb = mysql.connector.connect(
			host="35.227.29.52",
			database="USERS",
			user="root",
			password="mypassword123"
		)
		cursor = mydb.cursor()
		
		cursor.execute("SELECT * FROM CREDENTIALS WHERE Username='FinchTheBird'")

		row = cursor.fetchone()
		
		while row:
			print(row)
			row = cursor.fetchone()
	except Error as e:
		print(e)

	finally:
		cursor.close()

connect()	