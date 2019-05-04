db.people.updateMany(
  {first_name : "Antonio"},
  {$set: {hobby: "pingpong"}}
)

db.people.findOne(   {first_name : "Antonio"})