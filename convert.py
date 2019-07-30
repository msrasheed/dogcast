filepath = "toConvert.txt"

def kebabToCamel(l):
	parts = l.split("-")
	retVal = ""
	for part in parts:
		retVal += part[0].upper() + part[1:]
	return retVal

with open(filepath) as fp:
	for cnt, line in enumerate(fp):
		linenonew = line[0:len(line)-1]
		camel = kebabToCamel(linenonew)
		print ("public static final String " + camel + ' = "' + linenonew + '"') 
