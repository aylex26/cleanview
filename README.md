# cleanview


1.graphql

http://localhost:8080/graphiql?path=/graphql


query bookDetails {
bookById(id: "book-3") {
id
name
pageCount
author {
id
firstName
lastName
}
}
}