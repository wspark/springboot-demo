
#/bin/bash

echo "git push start"
git add .
git commit -m "ok"
git push
echo "git push end"

echo "book jar copy to deployments"
cp ./springboot-demo-book/target/book-0.0.1-SNAPSHOT.jar ./springboot-demo-book/deployments/
echo "member jar copy to deployments"
cp ./springboot-demo-member/target/member-0.0.1-SNAPSHOT.jar ./springboot-demo-member/deployments/

