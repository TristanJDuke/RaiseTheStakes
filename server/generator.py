import random

def generate():
	key = '%024x' % random.randrange(16**24)
	
	with open("keys.txt", "a") as myfile:
		myfile.write(key)
		myfile.write('\n')
	return key
print(generate())