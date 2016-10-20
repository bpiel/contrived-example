# contrived-example
An example project for demo'ing sayid. This is a taco vending machine.

curl localhost:8000/taco -d "{\"insert-coins\": [\"quarter\", \"quarter\", \"quarter\", \"quarter\"], \"push-button\": \"a1\"}" -H "Accept-Language: en" -H "Content-Type: application/json"
