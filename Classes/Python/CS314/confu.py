import sys

def confu(dictname, inname, outname):
	confulist = [] #this gets filled up with the words from dictionary, words that must be changed
	linewords = [] #this is from the file that will be rewritten

	#open and read in the dictionary
	#  the dictionary is a list of lists
	#	each sublist is a tab-separated list of
	#	two to three words
	file = open(dictname, 'r')

	for line in file.readlines():
		confulist.append(line.strip().split("\t"))

	file.close()

	#open up the file of text whose words you
	#	are going to replace 'inname'
	file = open(inname, 'r')
	
	#open up/create the file that will hold the
	#	modified version of 'infile'
	outfile = open(outname, 'w')

	#for each line in 'infile' ... 
	for line in file.readlines():
		#get a list of all the words in it ...
		linewords = line.strip().split(" ")
			# for each word in the file...
		for word in linewords:
			#--> fill in line here
				#for each sublist in the dictionary...
			for linedict in confulist:
				# ---> fill in line here
				length = len(linedict)
				if length == 2:
					if word == linedict[0]:
						line = line.replace(word, linedict[1])
					if word == linedict[1]:
						line = line.replace(word, linedict[0])

				if length == 3:
					if word == linedict[0]:
						line = line.replace(word, linedict[1])
					if word == linedict[1]:
						line = line.replace(word, linedict[2])
					if word == linedict[2]:
						line = line.replace(word, linedict[0])
					# if that word appears in a sublist,
					#   replace it with one of the other word(s) in
					#   that sublist
					# ---> fill in multiple lines here
		outfile.write(line)
			# write the modified line out to the 'outfile'
	file.close()
	outfile.close()


def main ( ):
	confu(sys.argv[1], sys.argv[2], sys.argv[3])

if __name__ == '__main__':
	main()

