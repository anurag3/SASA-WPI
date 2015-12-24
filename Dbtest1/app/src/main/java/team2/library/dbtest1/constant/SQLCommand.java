package team2.library.dbtest1.constant;

/**
 * Created by Anurag on 9/27/2015.
 */
/**
 * SQL commands
 * Including select/delete/update/insert
 */
public abstract class SQLCommand
{

    //list all data in books table
    public static String QUERY_1 = "SELECT POST.post_id as _id, st_first_name, st_last_name, post_title, post_desc FROM POST, STUDENT WHERE STUDENT.st_id==POST.st_id";
    //List the call numbers of books with the title ‘Database Management’
    public static String QUERY_2 = "INSERT INTO WISHLIST STUDENT.st_id as _id, post_id FROM POST, STUDENT WHERE STUDENT.st_id==POST.st_id";
    //select lbcallnum from LibBook where lbtitle like '%Database Management%'
    //list duedate and returned date
    public static String QUERY_category_spinner = "SELECT cat_id as _id, cat_name FROM CATEGORY";
    public static String QUERY_category_spinner_1 = "SELECT POST.post_id as _id, st_first_name,st_last_name, post_title, post_desc FROM CATEGORY, POST, STUDENT, ITEM WHERE STUDENT.st_id==POST.st_id AND POST.post_id=ITEM.post_id AND ITEM.cat_id=CATEGORY.cat_id AND cat_name='Study Books'";
    public static String QUERY_category_spinner_2 = "SELECT POST.post_id as _id, st_first_name, post_title, post_desc FROM CATEGORY, POST, STUDENT, ITEM WHERE STUDENT.st_id==POST.st_id AND POST.post_id=ITEM.post_id AND ITEM.cat_id=CATEGORY.cat_id AND cat_name='Smart Phone'";
    public static String QUERY_category_spinner_3 = "SELECT POST.post_id as _id, st_first_name, post_title, post_desc FROM CATEGORY, POST, STUDENT, ITEM WHERE STUDENT.st_id==POST.st_id AND POST.post_id=ITEM.post_id AND ITEM.cat_id=CATEGORY.cat_id AND cat_name='Car'";
    public static String QUERY_category_spinner_4 = "SELECT POST.post_id as _id, st_first_name, post_title, post_desc FROM CATEGORY, POST, STUDENT, ITEM WHERE STUDENT.st_id==POST.st_id AND POST.post_id=ITEM.post_id AND ITEM.cat_id=CATEGORY.cat_id AND cat_name='Furniture'";

    public static String QUERY_display_items = "SELECT item_name, item_desc, item_price, item_id FROM ITEM, POST WHERE ITEM.post_id=POST.post_id AND POST.post_id=?";
    public static String QUERY_3 ="";
    //query all students
    public static String QUERY_4 = "select stid, stname from Student";
    //list books with callnum = 2
    public static String QUERY_5 = "select * from LibBook where lbcallnum = 'c2'";
    //list all students who have checked out books
    public static String QUERY_6 = "select Student.stid, CheckOut.lbcallnum, stname from Student, CheckOut where Student.stid=CheckOut.stid";
    //list student id and callnum where book is not returned
    public static String QUERY_7 = "select stid, lbcallnum from CheckOut where coreturned='N'";



    public static String RETURN_BOOK = "update checkout set coreturned=? where stid=? and lbcallnum=?";
    public static String CHECK_BOOK = "insert into checkout(stid,lbcallnum,coduedate,coreturned) values(?,?,?,?)";

    //checkout summary
    public static String CHECKOUT_SUMMARY = "select strftime('%m',coduedate) as month,count(*) as total from checkout where strftime('%Y',coduedate)='2011' group by month order by total desc";

    public static String CHECKOUT_LIST = "select checkout.stid as _id, lbtitle, coduedate,coreturned,cofine,stname from checkout,student,libbook where student.stid=checkout.stid and libbook.lbcallnum=checkout.lbcallnum";
    public static String postCount = "select count(post_id) from post";
    public static String postInsert = "INSERT INTO POST(post_id,post_title,st_id,post_desc,post_hit_counter) VALUES (?,?,?,?,?)";
    public static String itemInsert = "INSERT INTO ITEM VALUES (?,?,?,?,?,?,?,?)";
    public static String postUpdater = "update post set post_hit_counter=? where post_id=?";

    public static String hotbuy = "SELECT POST.post_id as _id, st_first_name, st_last_name, post_title, post_desc FROM POST, STUDENT WHERE STUDENT.st_id==POST.st_id order by post_hit_counter desc" ;

    public static String newbuy = "SELECT POST.post_id as _id, st_first_name, st_last_name, post_title, post_desc FROM POST, STUDENT WHERE STUDENT.st_id==POST.st_id order by post_hit_counter desc" ;

    public static String profname= "SELECT STUDENT.st_id as _id, st_first_name, st_last_name,st_email,st_phone FROM STUDENT WHERE st_id=?";
    public static String profupdate= "UPDATE STUDENT SET st_first_name=?, st_last_name=?,st_email=?,st_pass=?,st_phone=? WHERE st_id=?";
}

