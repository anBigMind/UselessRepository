package com.oracle.start;

import com.oracle.entity.*;
import com.oracle.service.*;
import com.oracle.service.impl.*;
import com.oracle.utils.DateUtil;

import java.util.*;

public class LibraryMgtSys {
    Scanner input = new Scanner(System.in);
    AdminService adminService = new AdminServiceImpl();
    UserService userService = new UserServiceImpl();
    BookService bookService = new BookServiceImpl();
    BookTypeService bookTypeService = new BookTypeServiceImpl();
    RecordService recordService = new RecordServiceImpl();
    public static void main(String[] args) {
        LibraryMgtSys lms = new LibraryMgtSys();
        lms.mainMenu();
    }

    /**
     * 系统主菜单
     */
    private void mainMenu() {
        String c;
        do {
            System.out.println("===============================");
            System.out.println(
                    "\t  ╦  ┬┌┐ ┬─┐┌─┐┬─┐┬ ┬\n" +
                    "\t  ║  │├┴┐├┬┘├─┤├┬┘└┬┘\n" +
                    "\t  ╩═╝┴└─┘┴└─┴ ┴┴└─ ┴ ");
            System.out.println("===============================");
            System.out.println("------------选择登录------------");
            System.out.println("1.图书管理员登录");
            System.out.println("2.用户登录");
            System.out.println("3.注册新用户");
            System.out.println("0.退出系统");
            System.out.println("-------------------------------");
            System.out.println("请选择");
            c = input.next();
            switch (c){
                case "1":
                    System.out.println("请输入用户名");
                    String adminName = input.next();
                    System.out.println("请输入密码");
                    String adminPassword = input.next();
                    long start = System.currentTimeMillis();
                    Admin admin = adminService.adminLogin(adminName,adminPassword);
                    long end = System.currentTimeMillis();
                    System.out.println(end-start);
                    if (admin != null)
                        adminMainMenu(admin);
                    else
                        System.out.println("用户名或密码错误");
                    continue;
                case "2":
                    System.out.println("请输入用户名");
                    String userName = input.next();
                    System.out.println("请输入密码");
                    String userPassword = input.next();
                    User user = userService.userLogin(userName,userPassword);
                    if (user != null)
                        userMainMenu(user);
                    else
                        System.out.println("用户名或密码错误");
                    continue;
                case "3":
                    addUser();
                    continue;
                case "0":
                    break;
                default:
                    continue;
            }
            break;
        }while (true);
    }

    /**
     * 用户主菜单
     * @param user 登录的用户
     */
    private void userMainMenu(User user) {
        String c;
        do {
            System.out.println("-----------欢迎，"+user.getUserName()+"-----------");
            System.out.println("1.查看个人信息");
            System.out.println("2.修改密码");
            System.out.println("3.查看图书");
            System.out.println("4.查看借书记录");
            System.out.println("5.借出图书");
            System.out.println("6.归还图书");
            System.out.println("0.退出登录");
            System.out.println("-------------------------------");
            System.out.println("请选择");
            c = input.next();
            switch (c){
                case "1":
                    showUserInfo(user);
                    continue;
                case "2":
                    modifyUserPassword(user);
                    continue;
                case "3":
                    bookMenu();
                    continue;
                case "4":
                    showUserRecord(user.getId());
                    continue;
                case "5":
                    borrowBook(user);
                    continue;
                case "6":
                    userReturnBook(user);
                    continue;
                case "0":
                    break;
                default:
                    continue;
            }
            break;
        }while (true);
    }

    /**
     * 用户还书
     * @param user 要还书的用户
     */
    private void userReturnBook(User user) {
        if (returnBook(user.getId())){
            System.out.println("归还成功，记录已删除");
            user.setRecordNum(user.getRecordNum()-1);
        }else {
            System.out.println("没有借走的图书或记录不存在");
        }
    }

    /**
     * 根据用户id还书的方法
     * @param id 要还书用户的id
     */
    private boolean returnBook(int id) {
        if (userService.getUserRecordNumById(id) <= 0){
            return false;
        }
        showUserRecord(id);
        System.out.println("请选择要归还图书的记录编号");
        int recordId;
        try {
            recordId = input.nextInt();
        } catch (Exception e) {
            System.out.println("输入错误，只能输入记录编号");
            return false;
        }
        if (recordService.deleteRecordById(recordId,id) > 0){
            return true;
        }
        return false;
    }

    private void showUserRecord(int id) {
        System.out.println("---------------------------------------借书记录------------------------------------------");
        List<Record> records = recordService.getRecordById(id);
        if (records != null){
            for (Record record:records) {
                System.out.println("编号："+record.getId()+" 用户编号："+record.getUserId()+" ISBN号："+record.getISBN()+" 借出时间："+DateUtil.parseString(record.getBorrowTime())+" 归还时限："+DateUtil.parseString(record.getReturnTime()));
            }
        }
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * 用户借书
     * @param user 要借书的用户
     */
    private void borrowBook(User user) {
        if (user.getRecordNum()>=3){
            System.out.println("每个用户最多同时借出三本书，无法借更多的书了");
            return;
        }
        bookMenu();
        System.out.println("请输入要借出的图书ISBN号码");
        String isbn = input.next();
        if (recordService.createRecord(user.getId(),isbn) > 0){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE,30);
            System.out.println("借书成功，请在"+ DateUtil.parseString(calendar) +"之前归还");
            user.setRecordNum(user.getRecordNum()+1);
        }else {
            System.out.println("借书失败，图书不存在");
        }
    }

    /**
     * 管理员借出图书
     */
    private void lentBook() {
        printAllUsers();
        System.out.println("请选择要借书的用户编号");
        int id = 0;
        try {
            id = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("输入错误，只能输入用户编号");
        }
        if (userService.getUserRecordNumById(id) >= 3){
            System.out.println("每个用户最多同时借出三本书，无法借更多的书了");
            return;
        }
        bookMenu();
        System.out.println("请选择要借出的图书ISBN号码");
        String isbn = input.next();
        if (recordService.createRecord(id,isbn) > 0){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE,30);
            System.out.println("借书成功，请在"+ DateUtil.parseString(calendar) +"之前归还");
        }else {
            System.out.println("借书失败，图书不存在");
        }
    }

    /**
     * 查看图书菜单
     */
    private void bookMenu() {
        System.out.println("-----------图书查看菜单-----------");
        System.out.println("1.查看所有图书");
        System.out.println("2.根据分类查看图书");
        System.out.println("3.根据名称查看图书");
        System.out.println("0.返回");
        System.out.println("--------------------------------");
        System.out.println("请选择：");
        String c = input.next();
        switch (c) {
            case "1":
                printAllBooks();
                break;
            case "2":
                printBooksByType();
                break;
            case "3":
                printBooksByName();
                break;
            case "0":
                break;
            default:
                System.out.println("输入错误");
                break;
        }
    }

    /**
     * 根据名称查询图书（模糊查询）
     */
    private void printBooksByName() {
        System.out.println("请输入要查询的图书名称（模糊查询）");
        String name = input.next();
        List<Book> bookList = bookService.getBooksByName(name);
        printBookList(bookList);
    }

    /**
     * 根据分类显示图书
     */
    private void printBooksByType() {
        printAllBookTypes();
        System.out.println("请选择要查看的分类：");
        int id = 0;
        try {
            id = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("输入错误，只能输入分类编号");
            return;
        }
        List<Book> bookList = bookService.getBooksByTypeId(id);
        printBookList(bookList);
    }

    /**
     * 显示所有图书分类
     */
    private void printAllBookTypes() {
        List<BookType> allBookType = bookTypeService.getAllBookType();
        System.out.println("---------------图书分类---------------");
        if (allBookType != null){
            for (BookType bookType: allBookType) {
                System.out.println("编号："+bookType.getId()+"名称："+bookType.getTypeName());
            }
        }
        System.out.println("-------------------------------------");
    }

    /**
     * 显示所有图书
     */
    private void printAllBooks() {
        List<Book> bookList = bookService.getAllBooks();
        printBookList(bookList);
    }

    /**
     * 遍历打印图书对象集合
     * @param bookList 要打印的集合
     */
    private void printBookList(List<Book> bookList) {
        System.out.println("======================================图书信息=======================================");
        if (bookList != null){
            for (Book book: bookList) {
                System.out.println("ISBN："+book.getISBN()+"\t"+"书名："+book.getBookName()+"\t"+"作者："+book.getAuthor()+"\t"+"分类："+book.getBookTypeName()+"\t"+"出版社："+book.getPublishingHoush());
            }
        }
        System.out.println("====================================================================================");
    }

    /**
     * 修改用户密码
     * @param user 要修改密码的用户
     */
    private void modifyUserPassword(User user) {
        System.out.println("请输入要修改的密码");
        String pwd = input.next();
        System.out.println("请再次输入要修改的密码");
        String pwd1 = input.next();
        if (pwd.equals(pwd1)){
            if (userService.modifyUserPasswordById(user.getId(),pwd))
                System.out.println("修改成功");
            else
                System.out.println("修改失败");
        }else {
            System.out.println("两次密码不一致，修改失败");
        }
    }

    /**
     * 显示用户信息
     * @param user 要显示信息的用户
     */
    private void showUserInfo(User user) {
        System.out.print(" 编号："+user.getId());
        System.out.print(" 姓名："+user.getUserName());
        System.out.println(" 借书数量："+user.getRecordNum());
    }

    /**
     * 管理员主菜单
     * @param admin 登陆的管理员
     */
    private void adminMainMenu(Admin admin) {
        String c;
        do {
            System.out.println("-----------欢迎，"+admin.getAdminName()+"-----------");
            System.out.println("1.用户管理菜单");
            System.out.println("2.图书管理菜单");
            System.out.println("3.借阅管理菜单");
            System.out.println("4.修改密码");
            System.out.println("0.退出登录");
            System.out.println("-------------------------------");
            System.out.println("请选择");
            c = input.next();
            switch (c){
                case "1":
                    userMgtMenu();
                    continue;
                case "2":
                    bookMgtMenu();
                    continue;
                case "3":
                    recordMgtMenu();
                    continue;
                case "4":
                    modifyAdminPassword(admin);
                    continue;
                case "0":
                    break;
                default:
                    continue;
            }
            break;
        }while (true);
    }

    /**
     * 借阅管理菜单
     */
    private void recordMgtMenu() {
        String c;
        do {
            System.out.println("-----------借阅管理菜单----------");
            System.out.println("1.查看用户借书记录");
            System.out.println("2.借出图书");
            System.out.println("3.收回图书");
            System.out.println("0.返回");
            System.out.println("-------------------------------");
            System.out.println("请选择");
            c = input.next();
            switch (c){
                case "1":
                    showRecordByUserId();
                    continue;
                case "2":
                    lentBook();
                    continue;
                case "3":
                    adminReturnBook();
                    continue;
                case "0":
                    break;
                default:
                    continue;
            }
            break;
        }while (true);
    }

    /**
     * 图书管理菜单
     */
    private void bookMgtMenu() {
        String c;
        do {
            System.out.println("-----------图书管理菜单----------");
            System.out.println("1.查看图书");
            System.out.println("2.删除图书");
            System.out.println("3.新增图书");
            System.out.println("0.返回");
            System.out.println("-------------------------------");
            System.out.println("请选择");
            c = input.next();
            switch (c){
                case "1":
                    bookMenu();
                    continue;
                case "2":
                    deleteBook();
                    continue;
                case "3":
                    addBook();
                    continue;
                case "0":
                    break;
                default:
                    continue;
            }
            break;
        }while (true);
    }

    /**
     * 新增图书的方法
     */
    private void addBook() {
        Book book = null;
        System.out.println("请输入图书ISBN号码");
        String isbn = input.next();
        if (bookService.isIsbnExists(isbn)){
            System.out.println("该图书已存在");
            return;
        }
        System.out.println("请输入图书名称");
        String bookName = input.next();
        System.out.println("请输入图书作者");
        String bookAuthor = input.next();
        System.out.println("请输入图书出版社");
        String publishingHoush = input.next();
        printAllBookTypes();
        System.out.println("请选择图书类型");
        try {
            int bookTypeId = input.nextInt();
            if (bookTypeService.isBookTypeExists(bookTypeId)){
                book = new Book(isbn,bookName,publishingHoush,bookTypeId,bookAuthor,null);
            }else {
                System.out.println("该图书类型不存在");
            }
        } catch (InputMismatchException e) {
            System.out.println("输入错误，只能输入图书类型编号");
        }
        if (bookService.addBook(book)>0){
            System.out.println("添加成功");
        }
    }

    /**
     * 删除图书的方法
     */
    private void deleteBook() {
        bookMenu();
        System.out.println("请输入要删除图书的ISBN号码");
        String isbn = input.next();
        int res = 0;
        try {
            res = bookService.deleteBookByIsbn(isbn);
        } catch (Exception e) {
            System.out.println("该图书还有人正在借阅，无法删除");
            return;
        }
        if (res>0){
            System.out.println("删除成功");
        }else {
            System.out.println("图书不存在");
        }
    }

    /**
     * 用户管理菜单
     */
    private void userMgtMenu() {
        String c;
        do {
            System.out.println("-----------用户管理菜单----------");
            System.out.println("1.查看所有用户信息");
            System.out.println("2.根据名称查看用户");
            System.out.println("3.重置用户密码");
            System.out.println("4.新增用户");
            System.out.println("5.删除用户");
            System.out.println("0.返回");
            System.out.println("-------------------------------");
            System.out.println("请选择");
            c = input.next();
            switch (c){
                case "1":
                    printAllUsers();
                    continue;
                case "2":
                    searchUserByName();
                    continue;
                case "3":
                    resetUserPassword();
                    continue;
                case "4":
                    addUser();
                    continue;
                case "5":
                    deleteUser();
                    continue;
                case "0":
                    break;
                default:
                    continue;
            }
            break;
        }while (true);
    }

    /**
     * 根据名称查询用户（模糊查询）
     */
    private void searchUserByName() {
        System.out.println("请输入要查看的用户名称(模糊查询)");
        String userName = input.next();
        printUserList(userService.getUserByName(userName));
    }

    /**
     * 删除用户的方法
     */
    private void deleteUser() {
        printAllUsers();
        System.out.println("请输入要删除的用户编号");
        int id = input.nextInt();
        System.out.println("确认要删除吗，将会删除该用户所有借书记录(y/n)");
        if ("y".equalsIgnoreCase(input.next())){
            if (userService.deleteUserById(id)>0){
                System.out.println("删除成功");
            }else {
                System.out.println("该用户不存在");
            }
        }else {
            System.out.println("取消删除");
        }
    }

    /**
     * 添加用户的方法
     */
    private void addUser() {
        System.out.println("请输入用户名");
        String userName = input.next();
        if (userService.isNameExists(userName)){
            System.out.println("该用户已存在");
            return;
        }
        System.out.println("请输入密码");
        String pwd = input.next();
        System.out.println("请再次输入密码");
        String pwd1 = input.next();
        if (pwd1.equals(pwd)){
            if (userService.createNewUser(userName,pwd)>0){
                System.out.println("创建成功");
            }else {
                System.out.println("创建失败，未知错误");
            }
        }
    }

    /**
     * 管理员为用户还书
     */
    private void adminReturnBook() {
        printAllUsers();
        System.out.println("请输入要还书的用户编号");
        int id = 0;
        try {
            id = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("输入错误，只能输入用户编号");
            return;
        }
        if (returnBook(id)){
            System.out.println("归还成功，记录已删除");
        }else {
            System.out.println("没有借走的图书或记录不存在");
        }
    }

    /**
     * 根据用户编号查看借书记录
     */
    private void showRecordByUserId() {
        printAllUsers();
        System.out.println("请选择要查看借书记录的用户编号");
        int id = 0;
        try {
            id = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("输入错误，只能输入用户编号");
            return;
        }
        showUserRecord(id);
    }

    /**
     * 重置用户密码
     */
    private void resetUserPassword() {
        printAllUsers();
        System.out.println("请输入要重置密码的用户id");
        int id = 0;
        try {
            id = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("输入不合法，只能输入用户id");
            return;
        }
        if (userService.modifyUserPasswordById(id,"000000"))
            System.out.println("密码重置为000000");
        else
            System.out.println("修改失败,用户不存在");
    }

    /**
     * 修改管理员密码
     * @param admin 要修改密码的管理员
     */
    private void modifyAdminPassword(Admin admin) {
        System.out.println("请输入要修改的密码");
        String pwd = input.next();
        System.out.println("请再次输入要修改的密码");
        String pwd1 = input.next();
        if (pwd.equals(pwd1)){
            if (adminService.modifyAdminPassword(admin,pwd))
                System.out.println("修改成功");
        }else {
            System.out.println("两次密码不一致，修改失败");
        }
    }

    /**
     * 打印所有的用户信息
     */
    private void printAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        printUserList(allUsers);
    }

    private void printUserList(List<User> allUsers) {
        if (allUsers != null){
            for (User user: allUsers) {
                showUserInfo(user);
            }
        }
    }
}
