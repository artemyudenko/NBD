db.people.updateMany(
  {job : "Editor"},
  {$unset: {email: 1}}
)

db.people.findOne({job : "Editor"})