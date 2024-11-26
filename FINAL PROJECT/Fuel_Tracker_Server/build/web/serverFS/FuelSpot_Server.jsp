<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="database_AL.DBConnectionAL"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.Iterator"%>
<%
    DBConnectionAL conn = new DBConnectionAL();
    String key = request.getParameter("key").trim();
    System.out.println(key);

    //    ---------Customer_Registration-------
    if (key.equals("customer_register")) {
        String NAME = request.getParameter("c_name");
        String ADDRESS = request.getParameter("c_address");
        String PHONE = request.getParameter("c_phone");
        String EMAIL = request.getParameter("c_email");
        String PASSWORD = request.getParameter("c_pswd");
        String DOJ = request.getParameter("c_doj");

        String insertqry = "SELECT COUNT(*) AS COUNT FROM `customer_reg` WHERE email='" + EMAIL + "' OR phone='" + PHONE + "'";
//          String insertqry="SELECT *  FROM `server` WHERE email='aji@gmail.com'";
        System.out.println(insertqry);
        Iterator it = conn.getData(insertqry).iterator();
        if (it.hasNext()) {
            Vector v = (Vector) it.next();
            int max_vid = Integer.parseInt(v.get(0).toString());
            System.out.println(max_vid);

            if (max_vid == 0) {

                String qry = "INSERT INTO customer_reg(name, address, phone, email, dateofjoin ) VALUES ('" + NAME + "', '" + ADDRESS + "', '" + PHONE + "', '" + EMAIL + "', '" + DOJ + "')";
                String qry1 = "INSERT INTO login(reg_id,email,password,type,status) VALUES((select max(c_id)from customer_reg),'" + EMAIL + "','" + PASSWORD + "','CUSTOMER','1')";
                System.out.println(qry + "\n" + qry1);
                if (conn.putData(qry) > 0 && conn.putData(qry1) > 0) {

                    System.out.println("Registered");
                    out.println("Registered");
                } else {

                    System.out.println("Registertion Failed");
                    out.println("Registertion Failed");
                }

            } else {

                System.out.println("Already Exist");
                out.print("Already Exist");

            }
        } else {
            out.print("failed");
        }
    }

    //    ---------fuelstation_register-------
    if (key.equals("fuelstation_register")) {
        String NAME = request.getParameter("d_name");
        String OWNER = request.getParameter("d_ownername");
        String ADDRESS = request.getParameter("d_address");
        String PHONE = request.getParameter("d_phone");
        String EMAIL = request.getParameter("d_email");
        String PASSWORD = request.getParameter("d_pswd");
        String DISTRICT = request.getParameter("d_dst");
        String DOJ = request.getParameter("d_doj");
        String FLAT = request.getParameter("p_lat");
        String FLONG = request.getParameter("p_long");

        String insertqry = "SELECT COUNT(*) AS COUNT FROM `fuel_station_reg` WHERE email='" + EMAIL.trim() + "' OR phone='" + PHONE.trim() + "'";
//          String insertqry="SELECT *  FROM `server` WHERE email='aji@gmail.com'";
        System.out.println(insertqry);
        Iterator it = conn.getData(insertqry).iterator();
        if (it.hasNext()) {
            Vector v = (Vector) it.next();
            int max_vid = Integer.parseInt(v.get(0).toString());
            System.out.println(max_vid);

            if (max_vid == 0) {

                String qry = "INSERT INTO `fuel_station_reg`(name, owner_name, address, phone, email, district, dateofjoin, f_lat, f_long ) VALUES ('" + NAME + "', '" + OWNER + "', '" + ADDRESS + "', '" + PHONE + "', '" + EMAIL + "', '" + DISTRICT + "', '" + DOJ + "', '" + FLAT + "', '" + FLONG + "')";
                String qry1 = "INSERT INTO login(reg_id,email,password,type,status) VALUES((select max(fs_id)from `fuel_station_reg`),'" + EMAIL + "','" + PASSWORD + "','FUEL_STATION', '0')";

                System.out.println(qry + "\n" + qry1);
                if (conn.putData(qry) > 0 && conn.putData(qry1) > 0) {

                    System.out.println("Registered");
                    out.println("Registered");
                } else {

                    System.out.println("Registertion Failed");
                    out.println("Registertion Failed");
                }

            } else {

                System.out.println("Already Exist");
                out.print("Already Exist");

            }
        } else {
            out.print("failed");
        }
    }

    // -------------- Accessory shop --------------------------
    if (key.equals("accessoryshop_register")) {
        String NAME = request.getParameter("d_name");
        String OWNER = request.getParameter("d_ownername");
        String ADDRESS = request.getParameter("d_address");
        String PHONE = request.getParameter("d_phone");
        String EMAIL = request.getParameter("d_email");
        String PASSWORD = request.getParameter("d_pswd");
        String DISTRICT = request.getParameter("d_dst");
        String DOJ = request.getParameter("d_doj");
        String FLAT = request.getParameter("p_lat");
        String FLONG = request.getParameter("p_long");

        String insertqry = "SELECT COUNT(*) FROM `accessory_shop` WHERE email='" + EMAIL.trim() + "' OR phone='" + PHONE.trim() + "'";
//          String insertqry="SELECT *  FROM `server` WHERE email='aji@gmail.com'";
        System.out.println(insertqry);
        Iterator it = conn.getData(insertqry).iterator();
        if (it.hasNext()) {
            Vector v = (Vector) it.next();
            int max_vid = Integer.parseInt(v.get(0).toString());
            System.out.println(max_vid);

            if (max_vid == 0) {

                String qry = "INSERT INTO `accessory_shop`(name, owner_name, address, phone, email, district, dateofjoin, a_lat, a_long ) VALUES ('" + NAME + "', '" + OWNER + "', '" + ADDRESS + "', '" + PHONE + "', '" + EMAIL + "', '" + DISTRICT + "', '" + DOJ + "', '" + FLAT + "', '" + FLONG + "')";
                String qry1 = "INSERT INTO login(reg_id,email,password,type,status) VALUES((select max(as_id)from `accessory_shop`),'" + EMAIL + "','" + PASSWORD + "','SHOP', '1')";

                System.out.println(qry + "\n" + qry1);
                if (conn.putData(qry) > 0 && conn.putData(qry1) > 0) {

                    System.out.println("Registered");
                    out.println("Registered");
                } else {

                    System.out.println("Registertion Failed");
                    out.println("Registertion Failed");
                }

            } else {

                System.out.println("Already Exist");
                out.print("Already Exist");

            }
        } else {
            out.print("failed");
        }
    }

    //    ---------service_center_register-------
    if (key.equals("service_center_register")) {
        String NAME = request.getParameter("d_name");
        String OWNER = request.getParameter("d_ownername");
        String ADDRESS = request.getParameter("d_address");
        String PHONE = request.getParameter("d_phone");
        String EMAIL = request.getParameter("d_email");
        String PASSWORD = request.getParameter("d_pswd");
        String DISTRICT = request.getParameter("d_dst");
        String DOJ = request.getParameter("d_doj");
        String FLAT = request.getParameter("p_lat");
        String FLONG = request.getParameter("p_long");

        String insertqry = "SELECT COUNT(*) AS COUNT FROM `service_reg` WHERE email='" + EMAIL.trim() + "' OR phone='" + PHONE.trim() + "'";
//          String insertqry="SELECT *  FROM `server` WHERE email='aji@gmail.com'";
        System.out.println(insertqry);
        Iterator it = conn.getData(insertqry).iterator();
        if (it.hasNext()) {
            Vector v = (Vector) it.next();
            int max_vid = Integer.parseInt(v.get(0).toString());
            System.out.println(max_vid);

            if (max_vid == 0) {

                String qry = "INSERT INTO `service_reg`(name, owner_name, address, phone, email, district, dateofjoin, f_lat, f_long ) VALUES ('" + NAME + "', '" + OWNER + "', '" + ADDRESS + "', '" + PHONE + "', '" + EMAIL + "', '" + DISTRICT + "', '" + DOJ + "', '" + FLAT + "', '" + FLONG + "')";
                String qry1 = "INSERT INTO login(reg_id,email,password,type,status) VALUES((select max(sc_id)from `service_reg`),'" + EMAIL + "','" + PASSWORD + "','SERVICE_CENTER', '0')";

                System.out.println(qry + "\n" + qry1);
                if (conn.putData(qry) > 0 && conn.putData(qry1) > 0) {

                    System.out.println("Registered");
                    out.println("Registered");
                } else {

                    System.out.println("Registertion Failed");
                    out.println("Registertion Failed");
                }

            } else {

                System.out.println("Already Exist");
                out.print("Already Exist");

            }
        } else {
            out.print("failed");
        }
    }

    //    ---------Login-------
    if (key.trim().equals("login")) {

        String USERNAME = request.getParameter("U_name");
        String PASSWORD = request.getParameter("P_swd");

        String loginQry = "SELECT * FROM login WHERE email='" + USERNAME + "' AND password='" + PASSWORD + "' AND status='1'";
        System.out.println(loginQry);
        Iterator i = conn.getData(loginQry).iterator();

        if (i.hasNext()) {

            Vector v = (Vector) i.next();

            out.println(v.get(1) + "#" + v.get(4));

        } else {
            out.println("failed");

        }

    }

//    ---------------------viewFuelStationRqst--------------------- 
    if (key.equals("viewFuelStationRqst")) {
        String FID = request.getParameter("s_lid").trim();
        String qry = "SELECT * FROM `fuel_station_reg` fs, `login` l WHERE l.`reg_id` = fs.`fs_id` AND l.`status` = '0'";
        System.out.println("qry=" + qry);
        JSONArray array = new JSONArray();
        Iterator it = conn.getData(qry).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                JSONObject obj = new JSONObject();
                obj.put("sr_id", v.get(0).toString().trim());
                obj.put("s_name", v.get(1).toString().trim());
                obj.put("s_owner", v.get(2).toString().trim());
                obj.put("s_address", v.get(3).toString().trim());
                obj.put("s_phone", v.get(4).toString().trim());
                obj.put("s_district", v.get(5).toString().trim());
                obj.put("s_email", v.get(6).toString().trim());
                obj.put("s_doj", v.get(7).toString().trim());
                obj.put("s_status", v.get(8).toString().trim());
                array.add(obj);
            }
            out.println(array);
        } else {
            System.out.println("else id");
            out.print("failed");
        }
    }

    //    ---------------------viewServiceCenterRqst--------------------- 
    if (key.equals("viewServiceCenterRqst")) {
        String FID = request.getParameter("s_lid").trim();
        String qry = "SELECT * FROM `service_reg` sc, `login` l WHERE l.`reg_id` = sc.`sc_id` AND l.`status` = '0'";
        System.out.println("qry=" + qry);
        JSONArray array = new JSONArray();
        Iterator it = conn.getData(qry).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                JSONObject obj = new JSONObject();
                obj.put("sr_id", v.get(0).toString().trim());
                obj.put("s_name", v.get(1).toString().trim());
                obj.put("s_owner", v.get(2).toString().trim());
                obj.put("s_address", v.get(3).toString().trim());
                obj.put("s_phone", v.get(4).toString().trim());
                obj.put("s_district", v.get(5).toString().trim());
                obj.put("s_email", v.get(6).toString().trim());
                obj.put("s_doj", v.get(7).toString().trim());
                obj.put("s_status", v.get(8).toString().trim());
                array.add(obj);
            }
            out.println(array);
        } else {
            System.out.println("else id");
            out.print("failed");
        }
    }

    //    ---------deleteRequest-------
    if (key.equals("deleteRequest")) {
        String UID = request.getParameter("u_id");
        String RQSTID = request.getParameter("rqst_id");

        String str = "UPDATE `login` SET `status`='2' WHERE `reg_id`='" + RQSTID + "'";
        String str1 = "UPDATE `fuel_station_reg` SET `status`='REJECTED' WHERE `fs_id`='" + RQSTID + "'";
        if (conn.putData(str) > 0 && conn.putData(str1) > 0) {
            out.print("success");
        } else {
            out.print("failed");
        }

    }

    //    ---------deleteServiceRequest-------
    if (key.equals("deleteServiceRequest")) {
        String UID = request.getParameter("u_id");
        String RQSTID = request.getParameter("rqst_id");

        String str = "UPDATE `login` SET `status`='2' WHERE `reg_id`='" + RQSTID + "'";
        String str1 = "UPDATE `service_reg` SET `status`='REJECTED' WHERE `sc_id`='" + RQSTID + "'";
        if (conn.putData(str) > 0 && conn.putData(str1) > 0) {
            out.print("success");
        } else {
            out.print("failed");
        }

    }

    //    ---------approveRequest-------
    if (key.equals("approveRequest")) {
        String UID = request.getParameter("u_id");
        String RQSTID = request.getParameter("rqst_id");

        String str = "UPDATE `login` SET `status`='1' WHERE `reg_id`='" + RQSTID + "'";
        String str1 = "UPDATE `fuel_station_reg` SET `status`='APPROVED' WHERE `fs_id`='" + RQSTID + "'";
        if (conn.putData(str) > 0 && conn.putData(str1) > 0) {
            out.print("success");
        } else {
            out.print("failed");
        }

    }

    //    ---------approveServiceRequest-------
    if (key.equals("approveServiceRequest")) {
        String UID = request.getParameter("u_id");
        String RQSTID = request.getParameter("rqst_id");

        String str = "UPDATE `login` SET `status`='1' WHERE `reg_id`='" + RQSTID + "'";
        String str1 = "UPDATE `service_reg` SET `status`='APPROVED' WHERE `sc_id`='" + RQSTID + "'";
        if (conn.putData(str) > 0 && conn.putData(str1) > 0) {
            out.print("success");
        } else {
            out.print("failed");
        }

    }

//    ---------------------viewFuelStation--------------------- 
    if (key.equals("viewFuelStation")) {
        String SID = request.getParameter("fs_id").trim();
        String qry = "SELECT * FROM `fuel_station_reg` WHERE `status` = 'APPROVED'";
        System.out.println("qry=" + qry);
        JSONArray array = new JSONArray();
        Iterator it = conn.getData(qry).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                JSONObject obj = new JSONObject();
                obj.put("sr_id", v.get(0).toString().trim());
                obj.put("s_name", v.get(1).toString().trim());
                obj.put("s_owner", v.get(2).toString().trim());
                obj.put("s_address", v.get(3).toString().trim());
                obj.put("s_phone", v.get(4).toString().trim());
                obj.put("s_district", v.get(5).toString().trim());
                obj.put("s_email", v.get(6).toString().trim());
                obj.put("s_doj", v.get(7).toString().trim());
                obj.put("s_status", v.get(8).toString().trim());
                array.add(obj);
            }
            out.println(array);
        } else {
            System.out.println("else id");
            out.print("failed");
        }
    }

    //    ---------------------viewSeviceCenter--------------------- 
    if (key.equals("viewSeviceCenter")) {
        String SID = request.getParameter("fs_id").trim();
        String qry = "SELECT * FROM `service_reg` WHERE `status` = 'APPROVED'";
        System.out.println("qry=" + qry);
        JSONArray array = new JSONArray();
        Iterator it = conn.getData(qry).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                JSONObject obj = new JSONObject();
                obj.put("sr_id", v.get(0).toString().trim());
                obj.put("s_name", v.get(1).toString().trim());
                obj.put("s_owner", v.get(2).toString().trim());
                obj.put("s_address", v.get(3).toString().trim());
                obj.put("s_phone", v.get(4).toString().trim());
                obj.put("s_district", v.get(5).toString().trim());
                obj.put("s_email", v.get(6).toString().trim());
                obj.put("s_doj", v.get(7).toString().trim());
                obj.put("s_status", v.get(8).toString().trim());
                array.add(obj);
            }
            out.println(array);
        } else {
            System.out.println("else id");
            out.print("failed");
        }
    }

//    ---------------------viewUsers--------------------- 
    if (key.equals("viewUsers")) {
        String SID = request.getParameter("u_lid").trim();
        String qry = "SELECT * FROM `customer_reg` WHERE `status` = 'APPROVED'";
        System.out.println("qry=" + qry);
        JSONArray array = new JSONArray();
        Iterator it = conn.getData(qry).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                JSONObject obj = new JSONObject();
                obj.put("c_id", v.get(0).toString().trim());
                obj.put("c_name", v.get(1).toString().trim());
                obj.put("c_address", v.get(2).toString().trim());
                obj.put("c_phone", v.get(3).toString().trim());
                obj.put("c_email", v.get(4).toString().trim());
                obj.put("c_doj", v.get(5).toString().trim());
                obj.put("rqstStatus", "");
                array.add(obj);
            }
            out.println(array);
        } else {
            System.out.println("else id");
            out.print("failed");
        }
    }

    //    ---------------------getFuelStationUser--------------------- 
    if (key.equals("getFuelStationUser")) {
        String SID = request.getParameter("p_lid").trim();
        String qry = "SELECT * FROM `fuel_station_reg` WHERE `status` = 'APPROVED'";
        System.out.println("qry=" + qry);
        JSONArray array = new JSONArray();
        Iterator it = conn.getData(qry).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                JSONObject obj = new JSONObject();
                obj.put("sr_id", v.get(0).toString().trim());
                obj.put("s_name", v.get(1).toString().trim());
                obj.put("s_owner", v.get(2).toString().trim());
                obj.put("s_address", v.get(3).toString().trim());
                obj.put("s_phone", v.get(4).toString().trim());
                obj.put("s_district", v.get(5).toString().trim());
                obj.put("s_email", v.get(6).toString().trim());
                obj.put("s_doj", v.get(7).toString().trim());
                obj.put("s_status", v.get(8).toString().trim());
                obj.put("p_lat", v.get(9).toString().trim());
                obj.put("p_long", v.get(10).toString().trim());
                obj.put("rqstStatus", "");

                array.add(obj);
            }
            out.println(array);
        } else {
            System.out.println("else id");
            out.print("failed");
        }
    }

    //    ---------------------getServiceCenterUser--------------------- 
    if (key.equals("getServiceCenterUser")) {
        String SID = request.getParameter("p_lid").trim();
        String qry = "SELECT * FROM `service_reg` WHERE `status` = 'APPROVED'";
        System.out.println("qry=" + qry);
        JSONArray array = new JSONArray();
        Iterator it = conn.getData(qry).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                JSONObject obj = new JSONObject();
                obj.put("sr_id", v.get(0).toString().trim());
                obj.put("s_name", v.get(1).toString().trim());
                obj.put("s_owner", v.get(2).toString().trim());
                obj.put("s_address", v.get(3).toString().trim());
                obj.put("s_phone", v.get(4).toString().trim());
                obj.put("s_district", v.get(5).toString().trim());
                obj.put("s_email", v.get(6).toString().trim());
                obj.put("s_doj", v.get(7).toString().trim());
                obj.put("s_status", v.get(8).toString().trim());
                obj.put("p_lat", v.get(9).toString().trim());
                obj.put("p_long", v.get(10).toString().trim());
                obj.put("rqstStatus", "");

                array.add(obj);
            }
            out.println(array);
        } else {
            System.out.println("else id");
            out.print("failed");
        }
    }

    //    ---------------------getNearByServices--------------------- 
    if (key.equals("getNearByServices")) {
        String SID = request.getParameter("p_lid").trim();
        String qry = "SELECT * FROM `fuel_station_reg` WHERE `status` = 'APPROVED'";
        String qry1 = "SELECT * FROM `service_reg` WHERE `status` = 'APPROVED'";
        String qry2 = "SELECT * FROM hospital";

        System.out.println("qry=" + qry);
        System.out.println("qry=" + qry1);
        System.out.println("qry=" + qry2);

        JSONArray array = new JSONArray();
        JSONArray array1 = new JSONArray();
        JSONArray array2 = new JSONArray();

        Iterator it = conn.getData(qry).iterator();
        Iterator it1 = conn.getData(qry1).iterator();
        Iterator it2 = conn.getData(qry2).iterator();

        if (it.hasNext() || it1.hasNext() || it2.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                JSONObject obj = new JSONObject();
                obj.put("sr_id", v.get(0).toString().trim());
                obj.put("s_name", v.get(1).toString().trim());
                obj.put("s_owner", v.get(2).toString().trim());
                obj.put("s_address", v.get(3).toString().trim());
                obj.put("s_phone", v.get(4).toString().trim());
                obj.put("s_district", v.get(5).toString().trim());
                obj.put("s_email", v.get(6).toString().trim());
                obj.put("s_doj", v.get(7).toString().trim());
                obj.put("s_status", v.get(8).toString().trim());
                obj.put("p_lat", v.get(9).toString().trim());
                obj.put("p_long", v.get(10).toString().trim());
                obj.put("rqstStatus", "");
                obj.put("serviceType", "FUEL_STATION");

                array.add(obj);
            }

            while (it1.hasNext()) {
                Vector v = (Vector) it1.next();
                JSONObject obj = new JSONObject();
                obj.put("sr_id", v.get(0).toString().trim());
                obj.put("s_name", v.get(1).toString().trim());
                obj.put("s_owner", v.get(2).toString().trim());
                obj.put("s_address", v.get(3).toString().trim());
                obj.put("s_phone", v.get(4).toString().trim());
                obj.put("s_district", v.get(5).toString().trim());
                obj.put("s_email", v.get(6).toString().trim());
                obj.put("s_doj", v.get(7).toString().trim());
                obj.put("s_status", v.get(8).toString().trim());
                obj.put("p_lat", v.get(9).toString().trim());
                obj.put("p_long", v.get(10).toString().trim());
                obj.put("rqstStatus", "");
                obj.put("serviceType", "SERVICE_CENTER");

                array1.add(obj);
            }

            while (it2.hasNext()) {
                Vector v = (Vector) it2.next();
                JSONObject obj = new JSONObject();
                obj.put("h_id", v.get(0).toString().trim());
                obj.put("h_name", v.get(1).toString().trim());
                obj.put("h_location", v.get(2).toString().trim());
                obj.put("h_email", v.get(3).toString().trim());
                obj.put("h_phone", v.get(4).toString().trim());
                obj.put("h_lat", v.get(5).toString().trim());
                obj.put("h_long", v.get(6).toString().trim());
                obj.put("serviceType", "HOSPITAL");
                array2.add(obj);
            }

            out.println(array + "#" + array1+ "#" + array2);
        } else {
            System.out.println("else id");
            out.print("failed");
        }
    }

    //    ---------addAppointment-------
    if (key.equals("addAppointment")) {
        String UID = request.getParameter("u_id").trim();
        String SID = request.getParameter("s_id").trim();
        String FUEL = request.getParameter("fuel").trim();
        String DOJ = request.getParameter("p_doj").trim();
        String LAT = request.getParameter("rqstLat").trim();
        String LONG = request.getParameter("rqstLong").trim();

        String str = "INSERT INTO `fuel_request` (`c_id`, `fs_id`, `fuel` ,`rqst_time`, `status`, rqst_lat, rqst_long) VALUES('" + UID + "', '" + SID + "', '" + FUEL + "', '" + DOJ + "', 'REQUESTED', '" + LAT + "', '" + LONG + "')";
        System.out.println(str);
        if (conn.putData(str) > 0) {
            out.print("success");
        } else {
            out.print("failed");
        }
    }

    //    ---------addServiceAppointment-------
    if (key.equals("addServiceAppointment")) {
        String UID = request.getParameter("u_id").trim();
        String SID = request.getParameter("s_id").trim();
        String DESC = request.getParameter("desc").trim();
        String DOJ = request.getParameter("p_doj").trim();
        String LAT = request.getParameter("rqstLat").trim();
        String LONG = request.getParameter("rqstLong").trim();

        String str = "INSERT INTO `service_center_request` (`c_id`, `sc_id`, `desc` ,`rqst_time`, `status`, rqst_lat, rqst_long) VALUES('" + UID + "', '" + SID + "', '" + DESC + "', '" + DOJ + "', 'REQUESTED', '" + LAT + "', '" + LONG + "')";
        System.out.println(str);
        if (conn.putData(str) > 0) {
            out.print("success");
        } else {
            out.print("failed");
        }
    }

    //    ---------getAppointmentsStation-------
    if (key.equals("getAppointmentsStation")) {
        String SID = request.getParameter("s_id").trim();
        String qry = "SELECT c.*, fr.`c_rqst_id`, fr.`fuel`, fr.`status`, fr.`rqst_time`, fr.`rqst_lat`, fr.`rqst_long` FROM `fuel_request` fr, `fuel_station_reg` fs, `customer_reg` c WHERE fr.`c_id` = c.`c_id` AND fr.`fs_id` = fs.`fs_id` AND fr.`status` = 'REQUESTED' AND fr.`fs_id` = '" + SID + "'";
        System.out.println("qry=" + qry);
        JSONArray array = new JSONArray();
        Iterator it = conn.getData(qry).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                JSONObject obj = new JSONObject();
                obj.put("c_id", v.get(0).toString().trim());
                obj.put("c_name", v.get(1).toString().trim());
                obj.put("c_address", v.get(2).toString().trim());
                obj.put("c_phone", v.get(3).toString().trim());
                obj.put("c_email", v.get(4).toString().trim());
                obj.put("c_doj", v.get(5).toString().trim());
                obj.put("rqstStatus", v.get(9).toString().trim());
                obj.put("c_rqst_id", v.get(7).toString().trim());
                obj.put("fuelRqstd", v.get(8).toString().trim());
                obj.put("rqst_time", v.get(10).toString().trim());
                obj.put("rqst_lat", v.get(11).toString().trim());
                obj.put("rqst_long", v.get(12).toString().trim());
                array.add(obj);
            }
            out.println(array);
        } else {
            System.out.println("else id");
            out.print("failed");
        }
    }

//    ---------getAppointmentsUser-------
    if (key.equals("getAppointmentsUser")) {
        String UID = request.getParameter("u_id").trim();
        String qry = "SELECT fs.*, fr.* FROM `fuel_request` fr, `fuel_station_reg` fs, `customer_reg` c WHERE fr.`c_id` = c.`c_id` AND fr.`fs_id` = fs.`fs_id` AND fr.`status` = 'REQUESTED' AND fr.`c_id` = '" + UID + "'";
        System.out.println("qry=" + qry);
        JSONArray array = new JSONArray();
        Iterator it = conn.getData(qry).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                JSONObject obj = new JSONObject();
                obj.put("sr_id", v.get(0).toString().trim());
                obj.put("s_name", v.get(1).toString().trim());
                obj.put("s_owner", v.get(2).toString().trim());
                obj.put("s_address", v.get(3).toString().trim());
                obj.put("s_phone", v.get(4).toString().trim());
                obj.put("s_district", v.get(5).toString().trim());
                obj.put("s_email", v.get(6).toString().trim());
                obj.put("s_doj", v.get(7).toString().trim());
                obj.put("s_status", v.get(8).toString().trim());
                obj.put("p_lat", v.get(9).toString().trim());
                obj.put("p_long", v.get(10).toString().trim());
                obj.put("c_rqst_id", v.get(11).toString().trim());
                obj.put("fuelRqstd", v.get(14).toString().trim());
                obj.put("rqstStatus", v.get(16).toString().trim());
                array.add(obj);
            }
            out.println(array);
        } else {
            System.out.println("else id");
            out.print("failed");
        }
    }

    //    ---------getServiceAppointmentsUser-------
    if (key.equals("getServiceAppointmentsUser")) {
        String UID = request.getParameter("u_id").trim();
        String qry = "SELECT sr.*, scr.* FROM `service_center_request` scr, `service_reg` sr, `customer_reg` c WHERE scr.`c_id` = c.`c_id` AND scr.`sc_id` = sr.`sc_id` AND scr.`status` = 'REQUESTED' AND scr.`c_id` = '" + UID + "'";
        System.out.println("qry=" + qry);
        JSONArray array = new JSONArray();
        Iterator it = conn.getData(qry).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                JSONObject obj = new JSONObject();
                obj.put("sr_id", v.get(0).toString().trim());
                obj.put("s_name", v.get(1).toString().trim());
                obj.put("s_owner", v.get(2).toString().trim());
                obj.put("s_address", v.get(3).toString().trim());
                obj.put("s_phone", v.get(4).toString().trim());
                obj.put("s_district", v.get(5).toString().trim());
                obj.put("s_email", v.get(6).toString().trim());
                obj.put("s_doj", v.get(7).toString().trim());
                obj.put("s_status", v.get(8).toString().trim());
                obj.put("p_lat", v.get(9).toString().trim());
                obj.put("p_long", v.get(10).toString().trim());
                obj.put("c_rqst_id", v.get(11).toString().trim());
                obj.put("fuelRqstd", v.get(14).toString().trim());
                obj.put("rqstStatus", v.get(16).toString().trim());
                array.add(obj);
            }
            out.println(array);
        } else {
            System.out.println("else id");
            out.print("failed");
        }
    }

//    ---------------------addFeedBack--------------------- 
    if (key.equals("addFeedBack")) {
        String sub = request.getParameter("subject");
        String desc = request.getParameter("description");
        String uid = request.getParameter("uid");
        String type = request.getParameter("type");

        String qry = "INSERT INTO `feedback` (`uid`,`subject`,`description`,`status`, `type` ) VALUES (" + uid + ",'" + sub + "','" + desc + "','0', '" + type + "') ";
        System.out.println(qry);
        if (conn.putData(qry) > 0) {

            out.print("successful");
        } else {
            out.print("failed");
        }
    }

    //    ---------deleteUserAppointment-------
    if (key.equals("deleteUserAppointment")) {
        String UID = request.getParameter("u_id");
        String RQSTID = request.getParameter("rqst_id");

        String str = "UPDATE `fuel_request` SET `status`='REJECTED' WHERE `c_rqst_id`='" + RQSTID + "'";
        if (conn.putData(str) > 0) {
            out.print("success");
        } else {
            out.print("failed");
        }

    }

    //    ---------approveUserAppointment-------
    if (key.equals("approveUserAppointment")) {
        String UID = request.getParameter("u_id").trim();
        String RQSTID = request.getParameter("rqst_id").trim();
        String PRICE = request.getParameter("fuel_price").trim();

        String str = "UPDATE `fuel_request` SET `status`='APPROVED', station_rate='" + PRICE + "' WHERE `c_rqst_id`='" + RQSTID + "'";
        if (conn.putData(str) > 0) {
            out.print("success");
        } else {
            out.print("failed");
        }

    }

//    ---------------------addFeedBackSeller--------------------- 
    if (key.equals("addFeedBackSeller")) {
        String sub = request.getParameter("subject");
        String desc = request.getParameter("description");
        String uid = request.getParameter("uid");
        String type = request.getParameter("type");

        String qry = "INSERT INTO `feedback` (`uid`,`subject`,`description`,`status` ,`type`) VALUES (" + uid + ",'" + sub + "','" + desc + "','0', '" + type + "') ";
        System.out.println(qry);
        if (conn.putData(qry) > 0) {

            out.print("successful");
        } else {
            out.print("failed");
        }
    }

    //    ---------getApprovedAppointmentsStation-------
    if (key.equals("getApprovedAppointmentsStation")) {
        String SID = request.getParameter("s_id").trim();
        String qry = "SELECT c.*, fr.`c_rqst_id`, fr.`fuel`, fr.`status`, fr.`rqst_time` FROM `fuel_request` fr, `fuel_station_reg` fs, `customer_reg` c WHERE fr.`c_id` = c.`c_id` AND fr.`fs_id` = fs.`fs_id` AND fr.`status` = 'APPROVED' AND fr.`fs_id` = '" + SID + "'";
        System.out.println("qry=" + qry);
        JSONArray array = new JSONArray();
        Iterator it = conn.getData(qry).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                JSONObject obj = new JSONObject();
                obj.put("c_id", v.get(0).toString().trim());
                obj.put("c_name", v.get(1).toString().trim());
                obj.put("c_address", v.get(2).toString().trim());
                obj.put("c_phone", v.get(3).toString().trim());
                obj.put("c_email", v.get(4).toString().trim());
                obj.put("c_doj", v.get(5).toString().trim());
                obj.put("rqstStatus", v.get(9).toString().trim());
                obj.put("c_rqst_id", v.get(7).toString().trim());
                obj.put("fuelRqstd", v.get(8).toString().trim());
                obj.put("rqst_time", v.get(10).toString().trim());
                array.add(obj);
            }
            out.println(array);
        } else {
            System.out.println("else id");
            out.print("failed");
        }
    }

    //    ---------getPaidAppointmentsStation-------
    if (key.equals("getPaidAppointmentsStation")) {
        String SID = request.getParameter("s_id").trim();
        String qry = "SELECT c.*, fr.`c_rqst_id`, fr.`fuel`, fr.`status`, fr.`rqst_time` FROM `fuel_request` fr, `fuel_station_reg` fs, `customer_reg` c WHERE fr.`c_id` = c.`c_id` AND fr.`fs_id` = fs.`fs_id` AND fr.`status` = 'PAID' AND fr.`fs_id` = '" + SID + "'";
        System.out.println("qry=" + qry);
        JSONArray array = new JSONArray();
        Iterator it = conn.getData(qry).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                JSONObject obj = new JSONObject();
                obj.put("c_id", v.get(0).toString().trim());
                obj.put("c_name", v.get(1).toString().trim());
                obj.put("c_address", v.get(2).toString().trim());
                obj.put("c_phone", v.get(3).toString().trim());
                obj.put("c_email", v.get(4).toString().trim());
                obj.put("c_doj", v.get(5).toString().trim());
                obj.put("rqstStatus", v.get(9).toString().trim());
                obj.put("c_rqst_id", v.get(7).toString().trim());
                obj.put("fuelRqstd", v.get(8).toString().trim());
                obj.put("rqst_time", v.get(10).toString().trim());
                array.add(obj);
            }
            out.println(array);
        } else {
            System.out.println("else id");
            out.print("failed");
        }
    }

    //    ---------getApprovedAppointmentsUser-------
    if (key.equals("getApprovedAppointmentsUser")) {
        String UID = request.getParameter("u_id").trim();
        String qry = "SELECT fs.*, fr.* FROM `fuel_request` fr, `fuel_station_reg` fs, `customer_reg` c WHERE fr.`c_id` = c.`c_id` AND fr.`fs_id` = fs.`fs_id` AND fr.`status` = 'APPROVED' AND fr.`c_id` = '" + UID + "'";
        System.out.println("qry=" + qry);
        JSONArray array = new JSONArray();
        Iterator it = conn.getData(qry).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                JSONObject obj = new JSONObject();
                obj.put("sr_id", v.get(0).toString().trim());
                obj.put("s_name", v.get(1).toString().trim());
                obj.put("s_owner", v.get(2).toString().trim());
                obj.put("s_address", v.get(3).toString().trim());
                obj.put("s_phone", v.get(4).toString().trim());
                obj.put("s_district", v.get(5).toString().trim());
                obj.put("s_email", v.get(6).toString().trim());
                obj.put("s_doj", v.get(7).toString().trim());
                obj.put("s_status", v.get(8).toString().trim());
                obj.put("p_lat", v.get(9).toString().trim());
                obj.put("p_long", v.get(10).toString().trim());
                obj.put("c_rqst_id", v.get(11).toString().trim());
                obj.put("fuelRqstd", v.get(14).toString().trim());
                obj.put("rqstStatus", v.get(16).toString().trim());
                obj.put("station_rate", v.get(17).toString().trim());
                array.add(obj);
            }
            out.println(array);
        } else {
            System.out.println("else id");
            out.print("failed");
        }
    }

    //    ---------getApprovedServiceCenterAppointmentsUser-------
    if (key.equals("getApprovedServiceCenterAppointmentsUser")) {
        String UID = request.getParameter("u_id").trim();
        String qry = "SELECT sr.*, scr.* FROM `service_center_request` scr, `service_reg` sr, `customer_reg` c WHERE scr.`c_id` = c.`c_id` AND scr.`sc_id` = sr.`sc_id` AND scr.`status` = 'APPROVED' AND scr.`c_id`'" + UID + "'";
        System.out.println("qry=" + qry);
        JSONArray array = new JSONArray();
        Iterator it = conn.getData(qry).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                JSONObject obj = new JSONObject();
                obj.put("sr_id", v.get(0).toString().trim());
                obj.put("s_name", v.get(1).toString().trim());
                obj.put("s_owner", v.get(2).toString().trim());
                obj.put("s_address", v.get(3).toString().trim());
                obj.put("s_phone", v.get(4).toString().trim());
                obj.put("s_district", v.get(5).toString().trim());
                obj.put("s_email", v.get(6).toString().trim());
                obj.put("s_doj", v.get(7).toString().trim());
                obj.put("s_status", v.get(8).toString().trim());
                obj.put("p_lat", v.get(9).toString().trim());
                obj.put("p_long", v.get(10).toString().trim());
                obj.put("c_rqst_id", v.get(11).toString().trim());
                obj.put("fuelRqstd", v.get(14).toString().trim());
                obj.put("rqstStatus", v.get(16).toString().trim());
                array.add(obj);
            }
            out.println(array);
        } else {
            System.out.println("else id");
            out.print("failed");
        }
    }

    //    ---------getPaidAppointmentsUser-------
    if (key.equals("getPaidAppointmentsUser")) {
        String UID = request.getParameter("u_id").trim();
        String qry = "SELECT fs.*, fr.* FROM `fuel_request` fr, `fuel_station_reg` fs, `customer_reg` c WHERE fr.`c_id` = c.`c_id` AND fr.`fs_id` = fs.`fs_id` AND fr.`status` = 'PAID' AND fr.`c_id` = '" + UID + "'";
        System.out.println("qry=" + qry);
        JSONArray array = new JSONArray();
        Iterator it = conn.getData(qry).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                JSONObject obj = new JSONObject();
                obj.put("sr_id", v.get(0).toString().trim());
                obj.put("s_name", v.get(1).toString().trim());
                obj.put("s_owner", v.get(2).toString().trim());
                obj.put("s_address", v.get(3).toString().trim());
                obj.put("s_phone", v.get(4).toString().trim());
                obj.put("s_district", v.get(5).toString().trim());
                obj.put("s_email", v.get(6).toString().trim());
                obj.put("s_doj", v.get(7).toString().trim());
                obj.put("s_status", v.get(8).toString().trim());
                obj.put("p_lat", v.get(9).toString().trim());
                obj.put("p_long", v.get(10).toString().trim());
                obj.put("c_rqst_id", v.get(11).toString().trim());
                obj.put("fuelRqstd", v.get(14).toString().trim());
                obj.put("rqstStatus", v.get(16).toString().trim());
                obj.put("station_rate", v.get(17).toString().trim());
                array.add(obj);
            }
            out.println(array);
        } else {
            System.out.println("else id");
            out.print("failed");
        }
    }

//    ---------getFeedbackUser-------
    if (key.equals("getFeedbackUser")) {
        String UID = request.getParameter("p_lid").trim();
        String TYPE = request.getParameter("type").trim();
        String qry = "SELECT f.*, c.`name` FROM `customer_reg` c, `feedback` f WHERE f.`uid`=c.`c_id` AND f.`type`='CUSTOMER'";
        System.out.println("qry=" + qry);
        JSONArray array = new JSONArray();
        Iterator it = conn.getData(qry).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                JSONObject obj = new JSONObject();
                obj.put("fid", v.get(0).toString().trim());
                obj.put("uid", v.get(1).toString().trim());
                obj.put("subject", v.get(2).toString().trim());
                obj.put("description", v.get(3).toString().trim());
                obj.put("status", v.get(4).toString().trim());
                obj.put("type", v.get(5).toString().trim());
                obj.put("name", v.get(6).toString().trim());
                array.add(obj);
            }
            out.println(array);
        } else {
            System.out.println("else id");
            out.print("failed");
        }
    }

//    ---------getFeedbackSeller-------
    if (key.equals("getFeedbackSeller")) {
        String UID = request.getParameter("p_lid").trim();
        String TYPE = request.getParameter("type").trim();
        String qry = "SELECT f.*, fs.`name` FROM `fuel_station_reg` fs, `feedback` f WHERE f.`uid`=fs.`fs_id` AND f.`type`='FUEL_STATION'";
        System.out.println("qry=" + qry);
        JSONArray array = new JSONArray();
        Iterator it = conn.getData(qry).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                JSONObject obj = new JSONObject();
                obj.put("fid", v.get(0).toString().trim());
                obj.put("uid", v.get(1).toString().trim());
                obj.put("subject", v.get(2).toString().trim());
                obj.put("description", v.get(3).toString().trim());
                obj.put("status", v.get(4).toString().trim());
                obj.put("type", v.get(5).toString().trim());
                obj.put("name", v.get(6).toString().trim());
                array.add(obj);
            }
            out.println(array);
        } else {
            System.out.println("else id");
            out.print("failed");
        }
    }

//-------------------------------------------------------------------------------------
//    ---------------------ADD BANK---------------------    
    if (key.equals("newaccount")) {

        String result = "";
        System.out.println("Haiiiiiiiiiiiiii");
        String uid = request.getParameter("uid").toString().trim();

        System.out.println(uid);
        String pin = request.getParameter("pin").toString().trim();
        System.out.println(pin);

        String accno = request.getParameter("cardnum").toString().trim();
        String cvv = request.getParameter("cvv").toString();
        String balance = request.getParameter("balance").toString().trim();

        System.out.println(uid + " " + pin + " " + accno + " " + cvv + " " + balance);

        String checkqry = "SELECT * FROM `bank` WHERE `u_id`='" + uid + "'";

        String str = " INSERT INTO `bank` (`u_id`,`card_no`,`cvv_no`,`pin`,`balance`,`status`) "
                + "VALUES ('" + uid + "','" + accno + "','" + cvv + "','" + pin + "','" + balance + "','1')";

        System.out.println(str);

        Iterator itr = conn.getData(checkqry).iterator();
        if (itr.hasNext()) {

            System.out.println(checkqry);
            out.println("accountexists");

            String str3 = "UPDATE `bank` SET `balance`='" + balance + "', `card_no`='" + accno + "' , `cvv_no`='" + cvv + "' , `pin`='" + pin + "' WHERE u_id='" + uid + "'";
            if (conn.putData(str3) > 0) {
                out.println("success");
            } else {
                out.println("failed");
            }

        } else {
            if (conn.putData(str) > 0) {

                out.print("success");
                System.out.println("success");
            } else {

                out.println("failed");
            }
        }
    }

    //    ---------------------GET BANK---------------------  
    if (key.equals("getACcountDetails")) {
        String result = "";
        String uid = request.getParameter("uid").toString().trim();
        String qry = "SELECT * FROM `bank` WHERE `u_id`='" + uid + "'";
        System.out.println("qry  " + qry);
        Iterator itr = conn.getData(qry).iterator();
        if (itr.hasNext()) {
            Vector v = (Vector) itr.next();

            result = v.get(0) + "#" + v.get(1) + "#" + v.get(2) + "#" + v.get(3) + "#" + v.get(4) + "#" + v.get(5);
            out.println(result);
        } else {
            out.println("failed");
        }

    }

    //    ---------------------MAKE PAYMENT---------------------  
    if (key.equals("makepayment")) {
        String uid = request.getParameter("user_id").toString();
        String tot = request.getParameter("amt").toString();
        String req_id = request.getParameter("req_Id").trim().toString();
        String b_id = request.getParameter("b_id").toString();
        String bal = request.getParameter("bal").toString();
        String date = java.time.LocalDate.now().toString();

        double balance = Double.parseDouble(bal);
        double total = Double.parseDouble(tot);
        double new_balance = balance - total;
        System.out.println(total);
        String str1 = "update bank set balance = '" + balance + "' where b_id='" + b_id + "'";
        System.out.println(str1);

        String str2 = "UPDATE `fuel_request` SET `status` = 'PAID' WHERE `c_rqst_id` = '" + req_id + "'";
        System.out.println(str2);

        if (conn.putData(str1) > 0 && conn.putData(str2) > 0) {
            out.println("successfull");
        } else {
            out.println("failed");

        }

    }

//    ---------------------CHECK ACCOUNT---------------------  
    if (key.equals("accountCheck")) {
        String uid = request.getParameter("uid").toString().trim();
        String str1 = "select count(*) from bank where u_id = '" + uid + "'";
        System.out.println(str1);
        Iterator itr = conn.getData(str1).iterator();
        if (itr.hasNext()) {
            Vector v = (Vector) itr.next();
            int max_vid = Integer.parseInt(v.get(0).toString());
            System.out.println(max_vid);

            if (max_vid == 0) {
                System.out.println("abcd");
                out.println("Add Your Account Details");

            } else {
                String str = "SELECT * FROM bank where u_id = '" + uid + "'";
                System.out.println(str);
                String respo = "";
                Iterator itr1 = conn.getData(str).iterator();
                if (itr1.hasNext()) {
                    Vector vv = (Vector) itr1.next();
                    double deposit = Double.parseDouble(vv.get(5).toString());
                    System.out.println(deposit);
//                    Integer total = Integer.parseInt(totalcash);

                    out.println("failed");

                    respo = vv.get(0) + "#" + vv.get(2) + "#" + vv.get(3) + "#" + vv.get(4) + "#" + vv.get(5);
                    out.println(respo);

                } else {
                    out.println("failed");
                }
            }

        }
    }

//---------------    add mechanic  ---------------
    if (key.equals("addMechanic")) {
        String uid = request.getParameter("uid");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String qry = "INSERT INTO `mechanic`(`service_center`,`name`,`phone`,`email`,`Adhar`)VALUES('" + uid + "','" + name + "','" + phone + "','" + email + "','" + password + "') ";
        System.out.println(qry);
        if (conn.putData(qry) > 0) {

            out.print("successful");
        } else {
            out.print("failed");
        }
    }

//------------------- view Mechanic  -----------------------
    if (key.equals("viewMechanic")) {
        String SID = request.getParameter("u_lid").trim();
        String qry = "SELECT * FROM `mechanic` WHERE `service_center`='" + SID + "'";
        System.out.println("qry=" + qry);
        JSONArray array = new JSONArray();
        Iterator it = conn.getData(qry).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                JSONObject obj = new JSONObject();
                obj.put("mid", v.get(0).toString().trim());
                obj.put("s_id", v.get(1).toString().trim());
                obj.put("mech_name", v.get(2).toString().trim());
                obj.put("mech_phone", v.get(3).toString().trim());
                obj.put("mech_email", v.get(4).toString().trim());
                obj.put("adhar", v.get(5).toString().trim());
                array.add(obj);
            }
            out.println(array);
        } else {
            System.out.println("else id");
            out.print("failed");
        }
    }
    //    ---------getAppointmentsStation-------
    if (key.equals("getAppointmentsService")) {
        String SID = request.getParameter("s_id").trim();
        String qry = "SELECT c.*,sr.`c_rqst_id`,sr.desc,sr.status,sr.rqst_time,sr.rqst_lat,sr.rqst_long FROM `service_center_request` sr, `service_reg` ss,`customer_reg` c WHERE sr.`c_id` = c.`c_id` AND sr.sc_id=ss.sc_id AND sr.status='REQUESTED' AND sr.sc_id='" + SID + "'";
        System.out.println("qry=" + qry);
        JSONArray array = new JSONArray();
        Iterator it = conn.getData(qry).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                JSONObject obj = new JSONObject();
                obj.put("c_id", v.get(0).toString().trim());
                obj.put("c_name", v.get(1).toString().trim());
                obj.put("c_address", v.get(2).toString().trim());
                obj.put("c_phone", v.get(3).toString().trim());
                obj.put("c_email", v.get(4).toString().trim());
                obj.put("c_doj", v.get(5).toString().trim());
                obj.put("rqstStatus", v.get(9).toString().trim());
                obj.put("c_rqst_id", v.get(7).toString().trim());
                obj.put("desc", v.get(8).toString().trim());
                obj.put("rqst_time", v.get(10).toString().trim());
                obj.put("rqst_lat", v.get(11).toString().trim());
                obj.put("rqst_long", v.get(12).toString().trim());
                array.add(obj);
            }
            out.println(array);
        } else {
            System.out.println("else id");
            out.print("failed");
        }
    }

 if (key.equals("removeemployee")) {
        String UID = request.getParameter("u_id");
        String EMPID = request.getParameter("empid");

        String str = "DELETE FROM `mechanic` WHERE `service_center`='" + UID + "' AND `mid`='" + EMPID + "'";
        if (conn.putData(str) > 0) {
            out.print("success");
        } else {
            out.print("failed");
        }

    }

    
 

%>
