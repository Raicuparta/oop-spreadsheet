GROUPID="XXX"

TESTID="001 002 003 004 005 006 007 008 009 010 011 012 013 014 015 016 017 018 019 021 022 023 024 025 026 027 028 029 030 031 032 033 034 035 036 037 038 039 040 041 042 043 04 045 046 047 048 049 050 051 052 053 054 055 056 057 058 059 060 061 062 06 064 065 066 067 068 069 070 071 072 073 074 075 076 077 078 079 080 081 082 083 084 085 086 087 088 089 090 091 092 093 094 095 096 097 098 099 100"

cd ..

make -s clean

echo "##############################################"
echo "»»» PROJECT IS GOING TO COMPILE"
echo "»»» ... ... ..."
echo "»»» ... ... ..."

make -s >/dev/null

if [ ! -f calc-core/calc-core.jar ] || [ ! -f calc-textui/calc-textui.jar ]; then 

	echo
	echo "»»» ERROR COMPILLING - TESTING ABORTED!"
	echo "##############################################"
	exit	-1

fi

echo "»»» PROJECT SUCESSFULLY COMPILLED"
echo "##############################################"

cd proj-ef-tests

# Run each test
for id in $TESTID
do
	if [ ! -f A-$id-$id-M-ok.in ]; then
        # This test does not exist.
		continue
	fi
	
	echo "##############################################"
	echo "»»» Running test $id"
	
	# Check if this test requires an .import file
	if [ -f A-$id-$id-M-ok.import ]; then
		java -Dimport=A-$id-$id-M-ok.import -Din=A-$id-$id-M-ok.in -Dout=output/${GROUPID}_A-$id-$id-M-ok.out calc.textui.Calc
	else
		java -Din=A-$id-$id-M-ok.in -Dout=output/${GROUPID}_A-$id-$id-M-ok.out calc.textui.Calc
	fi
	
	# Output the result
	diff expected/A-$id-$id-M-ok.out output/${GROUPID}_A-$id-$id-M-ok.out
	
done
