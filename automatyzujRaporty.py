import csv
import os

#take data from file
def getData(path_and_file):
    data = ''
    with open(path_and_file, 'r') as fp:
        data = fp.read()

    return data

def whereIsData(file):
    '''oddaje sciezke do pliku o ktory nam chodzi'''
    current_path=os.getcwd()
    separator_len = len('\\kowalma3\\')
    cut = current_path.index('\\kowalma3\\')
    cut = cut + separator_len

    return current_path[:cut]+file
    


    
#pobieramy informacje o przetworzonych plikach
def createCSV(text):
    csv = ''
    firstLine = 'Date;Ticket;Action;\n' #action = done,other,return to CS
    csv = csv + firstLine
    t = text.split('\n')
    for line in t:

        if '2020-' not in line :
            continue
        date = line[:10]
        
        Ticket = ''
        
        if 'OCIN' in line:
            t = line.split(':')
            Ticket = t[1]
        elif 'OCRITM' in line:
            t = line.split(':')
            Ticket = t[3]
        else:
            continue

        
        Action = ''
        if ('processed' in line) or ('complete' in line) :
            Action = 'Done'
        elif 'return' in line:
            Action = 'Returned'
        else:
            Action = 'Other'

        newline = date +';'+ Ticket +';'+ Action +';\n'
        csv = csv + newline

    return csv

#
def saveCSV(text,name):
    
    
    f = open(name,'w')
    f.write(text)
    f.close()
#
raporty = [whereIsData('\\iAddress\\log_wasProcessed.txt'),
           whereIsData('\\SE_VAN\\log_wasProcessed.txt'),
           whereIsData('\\translator\\log_translator.txt')]
raport_name = ['iAddress.csv','se_van.csv','translator.csv']
i=0
for element in raporty:
    tmp_raport = getData(element)
    tmp_csv = createCSV(tmp_raport)
    saveCSV(tmp_csv, raport_name[i])
    i=i+1


###a tu bedzie szedl update na github
















