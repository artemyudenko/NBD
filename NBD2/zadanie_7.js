db.people.insert(
  {sex: "Male", first_name: "Artem", last_name: "Yudenko", job: "Engineer", email: "test@google.co.uk", location:{city: "Warsaw", address: {streetname: "Bla", streetnumber: "666"}}, description: "debugger", 
   height:180.00, weight: 80.00, birth_date:"2013-05-23T16:10:58Z", nationality: "None", credit:[{type:"visa", number:"123454", currency: "USD", balance: "10000000.00"}]
  }
)

db.people.find({first_name: "Artem"})