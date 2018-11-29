#! /bin/bash
	cp challenges/Dockerfile challenges/$1/$2 |& tee output.txt
        javac challenges/WriteFile.java |& tee output1.txt
	java -cp "challenges:." WriteFile $1 $2 $3 |& tee output2.txt
	docker-compose up --build $1-$2 |& tee output3.txt


