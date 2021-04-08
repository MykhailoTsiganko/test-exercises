dirname="myFiles"
fileAmount=7
fileToDelete=$((fileAmount-3))
1
#Create A Folder
mkdir $dirname

#Create 7 files
cd $dirname

counter=1
while [ $counter -le $fileAmount ]; do
  touch "file${counter}.txt"
  ((counter++))
done

#Delete 5 oldest files
counter=1
while [ $counter -le $fileToDelete ]; do
  rm "$(ls -t | tail -1)"
  ((counter++))
done

#Write text to files
for filename in *.txt; do
  echo "${filename}"
  FILE_NUMBER=$(echo "${filename}" | tr -dc '0-9')
  echo "I am file ${FILE_NUMBER}" >> "${filename}"
done
