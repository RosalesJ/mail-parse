def write_to_file(to_write,file_path):
	file = open(file_path, "a")
	file.write(to_write + "\n")
	file.close()

def zadder(string,length): # adds a zero before string if len(string) matches length
	if len(string) == length: return '0' + string
	else:                     return str(string)	

def date_extract(string):
	i = 1
	for char in str(string):
		if char == "]":
			return string[:i]
		else: i += 1
	
def date_parse(string):
	month_lst = {'January':1, 
			     'February':2, 
			     'March':3, 
			     'April':4, 
			     'May':5, 
			     'June':6, 
		         'July':7, 
			     'August':8, 
			     'September':9, 
			     'October':10, 
			     'November':11, 
			     'December':12}
	lst = []
	buffer = ''
	for char in str(string):
		if char != " " and char != ",":
			buffer = buffer + char
		else:
			if buffer != '': lst.append(buffer)
			buffer = ''	
	else: 
		lst.append(buffer)
		del(lst[0])
		del(lst[3])
		
	month = zadder(str(month_lst[lst[0]]), 1)
	day = zadder(lst[1], 1)	
	year = lst[2]
	time = zadder(lst[3], 7)
		
	date = '[' + month + '/' + day + '/' + year + '-' + time + lst[4]	
	return date

outfile = "/Users/CobyRosales/Library/Containers/com.apple.mail/Data/Documents/dateparsed.txt"
workfile = "/Users/CobyRosales/Library/Containers/com.apple.mail/Data/Documents/spamAdresses.txt"
addresses = [line for line in open(workfile) if line != '\n']
dates = []

for i,item in enumerate(addresses): #Checks for and deletes superfluous newlines
	if item[len(item) -1:] == "\n":
		addresses[i] = item[:len(item) - 1]

for x,item in enumerate(addresses):
	dates.append(date_parse(date_extract(item)))
	addresses[x] = item[len(date_extract(item)) + 1:]

n =0

for x,item in enumerate(dates):
	print(item + addresses[x])
	write_to_file(item + addresses[x], outfile)
	n += 1
	
print 'process finalized...'
print '--- addresses parsed: ' + str(n)
print "--- total addresses in output file: " + str(len([line for line in open(outfile)]))

f = open(workfile, 'w')
f.write('')
f.close()
