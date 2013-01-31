#!/bin/bash
source /etc/profile
SELF=$(cd $(dirname $0); pwd -P)/$(basename $0)
SELF_DIR=`dirname $SELF`

#project no
DWPROJECTNO="fpcms"

gitDir=/data/src/${DWPROJECTNO}
srcDir=${gitDir}/${DWPROJECTNO}
appDir=/data/app/${DWPROJECTNO}

echo "============================== del app dirs:shell config ====================="
rm -fr /data/app/${DWPROJECTNO}/shell/
rm -fr /data/app/${DWPROJECTNO}/config/

echo "============================== make app dirs ====================================="
mkdir -p ${appDir}/webapp
mkdir -p ${appDir}/lib
mkdir -p ${appDir}/htdocs
mkdir -p ${appDir}/config
mkdir -p ${appDir}/shell

echo "==============================*** update source file ***======================================="
cd ${gitDir}
git pull
cd ${srcDir}
svn up

#build
echo "==============================*** build project ***======================================="
cd ${srcDir}
mvn -Dmaven.test.skip=true -am clean install $1 $2 $3 $4 $5 $6 $7 $8 $9

#copy
echo "==============================*** cp to ${appDir} ***======================================"
#copy *.war *.jar and config
find . -maxdepth 4 -regex ".*/target/[^\/]*.war"  -exec cp -f {} ${appDir}/webapp/ \;
find . -maxdepth 4 -regex ".*/target/[^\/]*.jar"  -exec cp -f {} ${appDir}/lib/ \;
cp -Rf ${srcDir}/shell/ ${appDir}/
cp -Rf ${srcDir}/config/ ${appDir}/
find ${appDir}/shell/ -maxdepth 6 -regex "*.sh"  -exec chmod +x {} \;

#copy http static elements
cp -Rf ${srcDir}/web-home/src/main/webapp/* ${appDir}/htdocs/
cp -Rf ${srcDir}/web-admin/src/main/webapp/* ${appDir}/htdocs/

#del WEB-INF *.jsp
find ${appDir}/htdocs/ -type "d"  -name "WEB-INF" | xargs rm -rf
find ${appDir}/htdocs/ -type "f"  -name "*.jsp" | xargs rm -rf


echo "==============================================="
echo "                   BUILD END                   "
echo "==============================================="


