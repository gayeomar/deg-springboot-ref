#!/bin/sh
cd $(dirname $0)

cd ../service-03

./gradlew bootRun
ret=$?
if [ $ret -ne 0 ]; then
exit $ret
fi

cd ../service-02

./gradlew bootRun
ret=$?
if [ $ret -ne 0 ]; then
exit $ret
fi

cd ../service-01

./gradlew bootRun
ret=$?
if [ $ret -ne 0 ]; then
exit $ret
fi

exit