#! /bin/bash
envType=online
export envType
cd ..
APP_HOME=$(cd "$(dirname "$0")"; pwd)
JAVA_HOME=/usr/java/jdk1.6.0_31
LIB_HOME=$APP_HOME/lib
CONF_HOME=$APP_HOME/conf
MAIN=com.lenovo.push.data.speed.stream.StreamModule

cd $LIB_HOME
for l in `ls`
do
    CLASSPATH=$CLASSPATH:$LIB_HOME/$l
done

export CLASSPATH

java -Dmodule.home=$APP_HOME -Dlogfile.name=stream-module.log $MAIN $@