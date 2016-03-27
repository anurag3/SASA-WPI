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
    public static String QUERY_1 = "SELECT POST.post_id as _id, st_first_name, user_last_name, post_title, post_desc FROM POST, USER WHERE USER.user_id==POST.user_id";
    //List the call numbers of books with the title ‘Database Management’
    public static String QUERY_2 = "INSERT INTO WISHLIST USER.user_id as _id, post_id FROM POST, USER WHERE USER.user_id==POST.user_id";
    //select lbcallnum from LibBook where lbtitle like '%Database Management%'
    //list duedate and returned date
    public static String QUERY_category_spinner1 = "SELECT cat_id as _id, cat_name FROM CATEGORY";
    public static String QUERY_category_spinner_11 = "SELECT POST.post_id as _id, st_first_name,user_last_name, post_title, post_desc FROM CATEGORY, POST, USER, ITEM WHERE USER.user_id==POST.user_id AND POST.post_id=ITEM.post_id AND ITEM.cat_id=CATEGORY.cat_id AND cat_name='Study Books'";
    public static String QUERY_category_spinner_21 = "SELECT POST.post_id as _id, st_first_name, post_title, post_desc FROM CATEGORY, POST, USER, ITEM WHERE USER.user_id==POST.user_id AND POST.post_id=ITEM.post_id AND ITEM.cat_id=CATEGORY.cat_id AND cat_name='Smart Phone'";
    public static String QUERY_category_spinner_31 = "SELECT POST.post_id as _id, st_first_name, post_title, post_desc FROM CATEGORY, POST, USER, ITEM WHERE USER.user_id==POST.user_id AND POST.post_id=ITEM.post_id AND ITEM.cat_id=CATEGORY.cat_id AND cat_name='Car'";
    public static String QUERY_category_spinner_41 = "SELECT POST.post_id as _id, st_first_name, post_title, post_desc FROM CATEGORY, POST, USER, ITEM WHERE USER.user_id==POST.user_id AND POST.post_id=ITEM.post_id AND ITEM.cat_id=CATEGORY.cat_id AND cat_name='Furniture'";

    public static String QUERY_display_items = "SELECT item_name, item_desc, item_price, item_id FROM ITEM, POST WHERE ITEM.post_id=POST.post_id AND POST.post_id=?";
    public static String QUERY_3 ="";
    //query all students
    public static String QUERY_4 = "select stid, stname from USER";
    //list books with callnum = 2
    public static String QUERY_5 = "select * from LibBook where lbcallnum = 'c2'";
    //list all students who have checked out books
    public static String QUERY_6 = "select USER.stid, CheckOut.lbcallnum, stname from USER, CheckOut where USER.stid=CheckOut.stid";
    //list USER id and callnum where book is not returned
    public static String QUERY_7 = "select stid, lbcallnum from CheckOut where coreturned='N'";



    public static String RETURN_BOOK = "update checkout set coreturned=? where stid=? and lbcallnum=?";
    public static String CHECK_BOOK = "insert into checkout(stid,lbcallnum,coduedate,coreturned) values(?,?,?,?)";

    //checkout summary
    public static String CHECKOUT_SUMMARY = "select strftime('%m',coduedate) as month,count(*) as total from checkout where strftime('%Y',coduedate)='2011' group by month order by total desc";

    public static String CHECKOUT_LIST = "select checkout.stid as _id, lbtitle, coduedate,coreturned,cofine,stname from checkout,USER,libbook where USER.stid=checkout.stid and libbook.lbcallnum=checkout.lbcallnum";
    public static String postCount = "select count(post_id) from post";
    public static String postInsert = "INSERT INTO POST(post_id,post_title,user_id,post_desc,post_hit_counter) VALUES (?,?,?,?,?)";
    public static String itemInsert = "INSERT INTO ITEM VALUES (?,?,?,?,?,?,?,?)";
    public static String postUpdater = "update post set post_hit_counter=? where post_id=?";

    public static String hotbuy = "SELECT POST.post_id as _id, st_first_name, user_last_name, post_title, post_desc FROM POST, USER WHERE USER.user_id==POST.user_id order by post_hit_counter desc" ;

    public static String newbuy = "SELECT POST.post_id as _id, st_first_name, user_last_name, post_title, post_desc FROM POST, USER WHERE USER.user_id==POST.user_id order by post_hit_counter desc" ;

    public static String profname= "SELECT USER.user_id as _id, st_first_name, user_last_name,st_email,st_phone FROM USER WHERE user_id=?";
    public static String profupdate= "UPDATE USER SET st_first_name=?, user_last_name=?,st_email=?,st_pass=?,st_phone=? WHERE user_id=?";

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    //SASA@WPI Queries
    public static String logincheck = "SELECT user_id,user_email,user_pass FROM USER";

    //ShowBuyListActivity Queries
    public static String showbuylist = "SELECT POST.post_id as _id, user_first_name, user_last_name, post_title, post_desc FROM POST, USER WHERE USER.user_id==POST.user_id";

    public static String QUERY_category_spinner = "SELECT cat_id as _id, cat_name FROM CATEGORY";
    public static String QUERY_category_spinner_1 = "SELECT POST.post_id as _id, user_first_name, user_last_name, post_title, post_desc FROM CATEGORY, POST, USER, ITEM WHERE USER.user_id==POST.user_id AND POST.post_id=ITEM.post_id AND ITEM.cat_id=CATEGORY.cat_id AND cat_name='Study Books'";
    public static String QUERY_category_spinner_2 = "SELECT POST.post_id as _id, user_first_name, user_last_name, post_title, post_desc FROM CATEGORY, POST, USER, ITEM WHERE USER.user_id==POST.user_id AND POST.post_id=ITEM.post_id AND ITEM.cat_id=CATEGORY.cat_id AND cat_name='Smart Phone'";
    public static String QUERY_category_spinner_3 = "SELECT POST.post_id as _id, user_first_name, user_last_name, post_title, post_desc FROM CATEGORY, POST, USER, ITEM WHERE USER.user_id==POST.user_id AND POST.post_id=ITEM.post_id AND ITEM.cat_id=CATEGORY.cat_id AND cat_name='Car'";
    public static String QUERY_category_spinner_4 = "SELECT POST.post_id as _id, user_first_name, user_last_name, post_title, post_desc FROM CATEGORY, POST, USER, ITEM WHERE USER.user_id==POST.user_id AND POST.post_id=ITEM.post_id AND ITEM.cat_id=CATEGORY.cat_id AND cat_name='Furniture'";

    public static String gethitcount = "SELECT post_hit_counter FROM POST WHERE post_id=?";
    public static String updatehitcount = "UPDATE POST SET post_hit_counter=? WHERE post_id=?";



    //HotDeals Queries
    public static String showhotbuylist = "SELECT POST.post_id as _id, user_first_name, user_last_name, post_title, post_desc FROM POST, USER WHERE USER.user_id==POST.user_id order by post_hit_counter desc";

    //NewDeals Queries
    public static String shownewbuylist = "SELECT POST.post_id as _id, user_first_name, user_last_name, post_title, post_desc FROM POST, USER WHERE USER.user_id==POST.user_id order by post_id desc" ;


    //ProfilePage Queries
    public static String showprofile = "SELECT user_first_name, user_last_name,user_pass,user_email,user_phone FROM USER WHERE user_id=?";
    public static String updateprofile = "UPDATE USER SET user_first_name=?, user_last_name=?,user_email=?,user_pass=?,user_phone=? WHERE user_id=?";

    //WishList Queries
    public static String getwishlist = "SELECT wish_id FROM WISHLIST WHERE user_id=?";
    public static String showwishlist = "SELECT ITEM.item_id AS _id,item_name, item_price FROM ITEM,WISHLISTDETAILS,WISHLIST WHERE WISHLISTDETAILS.item_id=ITEM.item_id AND WISHLISTDETAILS.wish_id=WISHLIST.wish_id AND WISHLIST.wish_id=?";

    //MajorList Queries
    public static String getpostdetails1 = "SELECT post_title, post_desc FROM POST";
    public static String getposttitle = "SELECT post_title, post_desc FROM POST WHERE post_id=?";
    public static String getitemdetails = "SELECT ITEM.item_id AS _id, item_name, item_desc, item_price FROM ITEM, POST WHERE ITEM.post_id=POST.post_id AND POST.post_id=?";
    public static String getwishid = "SELECT wish_id FROM WISHLIST where user_id=?";
    public static String getwdid = "SELECT wd_id FROM WISHLISTDETAILS ORDER BY wd_id desc";
    public static String addtowishlist = "INSERT INTO WISHLISTDETAILS (wd_id,wish_id,item_id) VALUES (?,?,?)";

    //AddNewItem
    public static String getpostid = "SELECT post_id FROM POST ORDER BY post_id desc";
    public static String getitemid = "SELECT item_id FROM ITEM ORDER BY item_id desc";
    public static String insertpost = "INSERT INTO POST(post_id,post_title,post_desc,post_hit_counter,user_id) VALUES (?,?,?,?,?)";
    public static String insertitem = "INSERT INTO ITEM VALUES (?,?,?,?,?,?,?)";

    //PostSelectedPageTest
    public static String getpostdetails = "SELECT POST.post_id AS _id, post_title, post_desc FROM POST where post_id = ?";
    public static String getuserid = "SELECT user_id FROM POST where post_id = ?";
    public static String deleteitem = "DELETE FROM ITEM where item_id = ?";

    //UpdateItemDetails
    public static String getitemdetails_updatePage = "SELECT item_name, item_qoh, item_price, item_desc, cat_name FROM ITEM, CATEGORY WHERE ITEM.cat_id=CATEGORY.cat_id AND ITEM.item_id=?   ";
    public static String updateItemDetails = "UPDATE item set item_name=?, item_qoh=?, item_price=?, item_desc=?, cat_id=? where item_id=?";
}

