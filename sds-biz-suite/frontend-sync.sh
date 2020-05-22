
# Build for large projects
export NODE_OPTIONS=--max-old-space-size=10230
MODELNAME=sds
DEST_HOST=philip@demo.doublechaintech.com
# backup custom
rsync ${DEST_HOST}:~/resin-3.1.12/webapps/ROOT/admin/${MODELNAME}/*_custom.js public/
java -jar ~/githome/splitter.jar ${MODELNAME}

cd ~/githome/${MODELNAME}-biz-suite/bizui && yarn build && cd ../../
cd ~/githome/${MODELNAME}-biz-suite/bizui && rsync -avz   dist/* ${DEST_HOST}:~/resin-3.1.12/webapps/ROOT/admin/${MODELNAME}/

