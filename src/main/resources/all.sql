#namespace("admin")
  #sql("login")
    SELECT * FROM admin WHERE admin_name = ? AND admin_password = ?
  #end

  #sql("updateLoginTime")
    update admin set admin_logintime=? where admin_name = ?
  #end

  #sql("findById")
    SELECT * FROM admin WHERE admin_id = ?
  #end

  #sql("updateAdminPassword")
    UPDATE admin SET admin_password = ? WHERE admin_id = ?
  #end
#end

#namespace("record")
  #sql("findAll")
    SELECT * FROM record
  #end

  #sql("findById")
    SELECT * FROM record WHERE record_id = ?
  #end

  #sql("deleteById")
    DELETE FROM record WHERE record_id = ?
  #end
#end

#namespace("robot")
  #sql("findAll")
    select * from robot
  #end

  #sql("findById")
    SELECT * FROM robot WHERE robot_id = ?
  #end

  #sql("deleteById")
    DELETE FROM robot WHERE robot_id = ?
  #end
#end