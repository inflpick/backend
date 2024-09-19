/**
 * db.getSiblingDB() -> 현재 연결된 서버에 api-server 데이터베이스를 참조.
 * 만약, api-server 데이터베이스가 없으면, 실제 데이터베이스를 생성하지는 않지만, 이후 데이터가 삽입되면 데이터베이스가 자동 생성
 */
db = db.getSiblingDB('api-server');

db.createUser({
    user: "myuser",
    pwd: "mypassword",
    roles: [{ role: "readWrite", db: "api-server" }]
});

db.mycollection.insertMany([
    { name: "Alice", age: 25 },
    { name: "Bob", age: 30 },
    { name: "Charlie", age: 35 }
]);
