/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jdbclibrary_assign;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JdbcLibrary_Assign {
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/db1?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String user="root";
        String pass=user;
        
        Connection con = null;
        Statement stmt=null;
        String book_table_create = "create table book("
                + "b_id int primary key,"
                + "title varchar(21),"
                + "author varchar(21),"
                + "publisher varchar(21),"
                + "year_published int,"
                + "isbn varchar(52),"
                + "available_copies int"
                + ")";
        String book_table_drop="drop table book";
        String member_table_create ="create table member("
                + "m_id int primary key,"
                + "name varchar(21),"
                + "email varchar(51),"
                + "phno varchar(13),"
                + "membership_date date"
                + ")";
        
        String member_table_drop="drop table member";
        String transaction_table_create ="create table transaction("
                + "t_id int primary key auto_increment ,"
                + "b_id int  ,"
                + "m_id int  ,"
                + "borrow_date date,"
                + "return_date date,"
                + "status ENUM('borrowed', 'returned'),"
                + "FOREIGN KEY (b_id) REFERENCES book(b_id),"
                + "FOREIGN KEY (m_id) REFERENCES member(m_id)"
                + ")";
        String transaction_table_drop="drop table transaction";
        
        try{
            con = DriverManager.getConnection(url,user,pass);
            Statement drp= con.createStatement();
            //drp.execute(book_table_drop);
            //drp.execute(book_table_create);
            //drp.execute(member_table_drop);
            //drp.execute(member_table_create);
            //drp.execute(transaction_table_drop);drp.execute(transaction_table_create);
            //member demo = new member();
            Scanner input = new Scanner(System.in);
            
            //demo.add(con,input);
            //demo.update(con,input);
            //demo.delete(con,input);
            //demo.deletebook(con);
            //demo.queryBook(con, input);
            //demo.queryMember(con, input);
            //transaction demo  = new transaction(); 
            //demo.queryTran(con, input);
            //demo.borrow(con, input);
            //demo.return_borrow(con, input);
            while (true) {
            System.out.println("Library Management:");
            System.out.println("1. Book Management");
            System.out.println("2. Member Management");
            System.out.println("3. Transaction Management");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine(); // Consume newline
 
            switch (choice) {
                case 1:  // Book Management
                    bookManagementMenu(con, input);
                    break;
                case 2:  // Member Management
                    memberManagementMenu(con, input);
                    break;
                case 3:  // Transaction Management
                    transactionManagementMenu(con, input);
                    break;
                case 4:  // Exit
                    System.out.println("Exiting...");
                    return;  // Exit the program
                default:
                    System.out.println("Invalid choice. Please try again.");
                            }        
                }        
             
        }catch(SQLException e){
            System.out.println("fail to some error : "+e);
            e.printStackTrace();
        }catch(Exception e){
            System.out.println("fail to some error : "+e);
            e.printStackTrace();
        }
        
        
    }
 static void transactionManagementMenu(Connection con, Scanner input) {
    transaction demo = new transaction(); // Create an instance of the transaction class

    while (true) {
        System.out.println("\nYou have chosen Transaction Management:");
        System.out.println("1. Borrow a book");
        System.out.println("2. Return a book");
        System.out.println("3. Query transactions");
        System.out.println("4. Return to main menu");
        System.out.print("Enter your choice: ");
        
        int choice = input.nextInt();
        input.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                demo.borrow(con, input);
                break;
            case 2:
                demo.return_borrow(con, input);
                break;
            case 3:
                demo.queryTran(con, input);
                break;
            case 4:
                return;  // Return to the main menu
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}
    
   static  void bookManagementMenu(Connection con,Scanner input) {
        while (true) {
            //Scanner input = new Scanner(System.in);
            System.out.println("\nYou have chosen Book Management:");
            System.out.println("1. Add a new book");
            System.out.println("2. Update book details");
            System.out.println("3. Delete a book");
            System.out.println("4. Query books");
            System.out.println("5. Return to main menu");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine(); // Consume newline
            book demo = new book();
            switch (choice) {
                case 1:
                    demo.add(con,input);
                    break;
                case 2:
                    demo.update(con,input);
                    break;
                case 3:
                    demo.deletebook(con);
                    break;
                case 4:
                    demo.queryBook(con, input);
                    break;
                case 5:
                    return;  // Return to the main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


  static void memberManagementMenu(Connection con,Scanner input) {
        while (true) {
            System.out.println("\nYou have chosen Member Management:");
            System.out.println("1. Add a new member");
            System.out.println("2. Update member details");
            System.out.println("3. Delete a member");
            System.out.println("4. Query members");
            System.out.println("5. Return to main menu");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine(); // Consume newline
            member demo = new member();

            switch (choice) {
                case 1:
                    demo.add(con,input);
                    break;
                case 2:
                    demo.update(con,input);
                    break;
                case 3:
                    demo.delete(con,input);
                    break;
                case 4:
                    demo.queryMember(con,input);
                    break;
                case 5:
                    return;  // Return to the main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
   
   
}

  


class book {
    
    PreparedStatement prep= null;
    
   void add(Connection con,Scanner input){
       try {
           String sql = "insert into book values(?,?,?,?,?,?,?)";
            prep= con.prepareStatement(sql);
            System.out.print("Enter Author: ");
            prep.setString(3, input.nextLine());
            System.out.print("Enter Publisher: ");
            prep.setString(4, input.nextLine());
            System.out.print("Enter Title: ");
            prep.setString(2, input.nextLine());
            System.out.print("Enter ISBN: ");
            prep.setString(6, input.nextLine());
            System.out.print("Enter Year Published: ");
            prep.setInt(5, input.nextInt());
            System.out.print("Enter Available Copies:");
            prep.setInt(7, input.nextInt());
            System.out.print("Enter Book ID:");
            prep.setInt(1, input.nextInt()); 
            input.nextLine();
            int result=prep.executeUpdate();
            if(result>0){
                System.out.println("new book added to db");
            }
            else{
                System.out.println("fail to add new booj to db ");
            }
        prep.close();
        
       }catch(SQLException e){
           
       }
   }
  
   void update(Connection con, Scanner inputs) {
    System.out.println("Select the bid to update:");
    int bid_target = inputs.nextInt();
    inputs.nextLine(); // Consume the newline character

    System.out.println("Select the field to update:");
    System.out.println("1. Title\n2. Author\n3. Publisher\n4. Year Published\n5. ISBN\n6. Available Copies");
    System.out.print("Enter your choice (1-6): ");
    int choice = inputs.nextInt();
    inputs.nextLine();
    
     String columnName = "";
     switch (choice) {         
    case 1:             
        columnName = "title";             
        break;         
    case 2:             
        columnName = "author";             
        break;         
    case 3:             
        columnName = "publisher";             
        break;         
    case 4:             
        columnName = "year_published";             
        break;         
    case 5:             
        columnName = "isbn";             
        break;         
    case 6:             
        columnName = "available_copies";             
        break;         
    default:             
        System.out.println("Invalid choice.");             
        return;     
}
    
    
    String upd="update book set "+columnName+"=? where b_id ="+bid_target;
    try {
        prep = con.prepareStatement(upd);
        //PreparedStatement prep = con.prepareStatement(upd); 
        System.out.print("enter the new "+columnName+" :");
        prep.setString(1,inputs.nextLine());
        if(prep.executeUpdate()>0){
            System.out.print("sucess");
        }
    }catch(Exception e){
        e.printStackTrace();
    }
   }
   
   
    int queryBook(Connection con, Scanner inputs) {
        System.out.println("How would you like to query the book?");
        System.out.println("1. Title\n2. Author\n3. ISBN");
        System.out.print("Enter your choice (1-3): ");
        int choice = inputs.nextInt();
        inputs.nextLine(); // shows errs so the newline character
        String cols ="";int found_book=-1;
        switch (choice) {
            case 1:
                   cols ="title";
                   break;
            case 2:
                cols ="author";
                break;
            case 3:
               cols ="isbn";
                break;
            default:
                System.out.println("Invalid choice.");
                return -1; 
        }
        String querySql = "SELECT * FROM book WHERE "+cols+" = ?";
        try {
            
            prep= con.prepareStatement(querySql);
            System.out.print("Enter the "+cols+": ");
            prep.setString(1,inputs.nextLine());
            ResultSet res = prep.executeQuery();
            found_book=printResults(res);
            return found_book;
        }   catch(SQLException e){
            e.printStackTrace();
        }
        return found_book;
    }

     

     int printResults(ResultSet rs) throws SQLException {
        boolean found = false;
        while (rs.next()) {
            found = true;
            System.out.println("-----------------------------");
            System.out.println("Query result :" );
            System.out.println("Book ID: " + rs.getInt("b_id"));
            
            System.out.println("Title: " + rs.getString("title"));
            System.out.println("Author: " + rs.getString("author"));
            System.out.println("Publisher: " + rs.getString("publisher"));
            System.out.println("Year Published: " + rs.getInt("year_published"));
            System.out.println("ISBN: " + rs.getString("isbn"));
            System.out.println("Available Copies: " + rs.getInt("available_copies"));
            System.out.println("-----------------------------");
        }
        if (!found) {
            System.out.println("No books found matching the criteria.");
            return -1;
        }
        return 1;// if found 
    }
     
     void deletebook(Connection con){
         Scanner input=new Scanner(System.in);
         
         System.out.println("Enter the book id to delete :");
         String sqldel= "delete from book where b_id="+input.nextLine();
         try{
             Statement stmt = con.createStatement();
             
             int res = stmt.executeUpdate(sqldel);
             
             if(res>0){
                 System.out.println("dlelted "+res+" row");
             }else{
                 System.out.println("the book id to delete does not exist ");
             }
         }catch (SQLException e){
             e.printStackTrace();
         }
     }
  
}

class member{
    
    Statement stmt = null;
    PreparedStatement prep =null;
    ResultSet res = null;
    void add (Connection con,Scanner input){
        String add_memb_sql ="insert into member(m_id,name,email,phno,membership_date) values "
                + "(?,?,?,?,?)"; 
        try {
            prep = con.prepareStatement(add_memb_sql);
            //inputs 
            
            System.out.print("Enter Name: ");
            prep.setString(2, input.nextLine());
            System.out.print("Enter Email: ");
            prep.setString(3, input.nextLine());
            System.out.print("Enter Phone Number: ");
            prep.setString(4, input.nextLine());
            System.out.print("Enter Membership Date (YYYY-MM-DD): ");
            prep.setString(5, input.nextLine());
            System.out.print("Enter Member ID (m_id): ");
            prep.setInt(1, input.nextInt());
            if(prep.executeUpdate()>0){
                System.out.println("added the new book ");
            }else{
                System.out.println(" book was not added ");
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    void queryMember(Connection con,Scanner input){
        String cols ="";
        
        System.out.println(" press 1 to search by name \n press 2 to search by email ");
        int choise =input.nextInt();
        if(choise==1){
            cols = "name";
        }else {
            cols = "email";
        }
        try {
        String query = "select * from member where "+cols+"= ?";
        prep = con.prepareStatement(query);
        System.out.println("enter the "+cols+" :");
        String target =input.nextLine();
        prep.setString(1,input.nextLine());
        res = prep.executeQuery();
        printMemeber(res);
        }catch(SQLException e ){
            e.printStackTrace();
        }
    }
    void printMemeber (ResultSet res)throws SQLException {
        boolean found = false;
        while(res.next()){
            found= true;
            System.out.print("------------------------------------------");
            System.out.print("Member ID (m_id): "+res.getInt("m_id")+"\n");
            System.out.print("Name: "+res.getString("name")+"\n");
            System.out.print("Email: "+res.getString("email")+"\n");
            System.out.print("Phone Number: "+res.getString("phno")+"\n");
            System.out.print("Membership Date (YYYY-MM-DD): "+res.getString("membership_date")+"\n");
            System.out.print("------------------------------------------");
        }
        if(!found ){
            System.out.print("no members records present ");
        }

    }
    
    	void update(Connection con, Scanner input) {
                
		System.out.print("Enter Member ID (m_id) to update: ");
		int m_id = input.nextInt();
		input.nextLine();  // error so  the newline character
		
		System.out.println("Select the field to update:");
		System.out.println("1. Member ID (m_id)\n2. Name\n3. Email\n4. Phone Number\n5. Membership Date");
		System.out.print("Enter your choice (1-5): ");
		int choice = input.nextInt();
		input.nextLine();  // error so  the newline character

		String columnName = "";

		switch (choice) {
			case 1:
				columnName = "m_id";
				break;
			case 2:
				columnName = "name";
				break;
			case 3:
				columnName = "email";
				break;
			case 4:
				columnName = "phno";
				break;
			case 5:
				columnName = "membership_date";
				break;
			default:
				System.out.println("Invalid choice.");
				return;
		}
                try {
                    String qury= "update member set "+columnName+"=? where m_id ="+m_id;
                prep = con.prepareStatement(qury);
                System.out.println("enter new"+columnName+" :");
                if(columnName =="m_id"){
                    prep.setInt(1,input.nextInt());
                }
                else{
                    prep.setString(1,input.nextLine());
                }
                int result = prep.executeUpdate();
                if(result>0){
                    System.out.println("success updated ");
                }else {
                    System.out.println("not  updated  record my not exist ");
                }
                }catch (SQLException e){
                    e.printStackTrace();
                }
	}

    void delete(Connection con,Scanner input){
         
         
         System.out.println("Enter the member id to delete :");
         String sqldel= "delete from member where m_id="+input.nextInt();
         try{
            stmt = con.createStatement();
             
             int res = stmt.executeUpdate(sqldel);
             
             if(res>0){
                 System.out.println("dlelted "+res+" row");
             }else{
                 System.out.println("the member id to delete does not exist ");
             }
         }catch (SQLException e){
             e.printStackTrace();
         }
     }
   
}
class transaction {
    Statement stmt = null;
    PreparedStatement prep = null;
    ResultSet res = null;

    
    void borrow(Connection con, Scanner input) {
        try {
            // member's name
            System.out.print("Enter member's name: ");
            String memberName = input.nextLine();

            //  if the member exit by name
            String memberQuery = "SELECT * FROM member WHERE name = ?";
            prep = con.prepareStatement(memberQuery);
            prep.setString(1, memberName);
            res = prep.executeQuery();

            if (!res.next()) {
                System.out.println("Member not found!");
                return;
            }

            // take member's ID
            int m_id = res.getInt("m_id");

            //  books title
            System.out.print("Enter book title to borrow: ");
            String bookTitle = input.nextLine();

            // see if the book exists by title
            String bookQuery = "SELECT * FROM book WHERE title = ?";
            prep = con.prepareStatement(bookQuery);
            prep.setString(1, bookTitle);
            res = prep.executeQuery();

            if (!res.next()) {
                System.out.println("Book not found!");
                return;
            }

            // see books id and available copies
            int b_id = res.getInt("b_id");
            int availableCopies = res.getInt("available_copies");

            if (availableCopies == 0) {
                System.out.println("Sorry, the book is currently unavailable. copes not less or not sufficent");
                return;
            }

            //  how many copies the member wants to borrow
            System.out.print("Enter the number of copies you want to borrow: ");
            int copiesToBorrow = input.nextInt();
            input.nextLine(); // error coms newline character

            if (copiesToBorrow > availableCopies) {
                System.out.println("Sorry, there are not enough copies available.");
                return;
            }

            // to borrow the book adn insert the transaction record
            
            String insertTransaction = "INSERT INTO transaction (t_id, b_id, m_id, borrow_date, status) VALUES (NULL, ?, ?, CURDATE(), 'borrowed')";
            prep = con.prepareStatement(insertTransaction);
            prep.setInt(1, b_id);
            prep.setInt(2, m_id);
            prep.executeUpdate();

            // minus  number of available copies in the book table
            String updateBook = "UPDATE book SET available_copies = available_copies - ? WHERE b_id = ?";
            prep = con.prepareStatement(updateBook);
            prep.setInt(1, copiesToBorrow);
            prep.setInt(2, b_id);
            int rowsUpdated = prep.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Transaction successful! Member '" + memberName + "' borrowed " + copiesToBorrow + " copy(ies) of the book titled '" + bookTitle + "'.");
                return;
            } else {
                System.out.println("Failed to borrow the book.");
                return;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    void return_borrow(Connection con, Scanner input) {
        try {
            // member's name
            System.out.print("Enter member's name: ");
            String memberName = input.nextLine();

            //  if the member exit by name
            String memberQuery = "SELECT * FROM member WHERE name = ?";
            prep = con.prepareStatement(memberQuery);
            prep.setString(1, memberName);
            res = prep.executeQuery();

            if (!res.next()) {
                System.out.println("Member not found!");
                return;
            }

            // take member's ID
            int m_id = res.getInt("m_id");

            

            // see if the any book borrowed by title
            String borrowed_book_Query = "SELECT * FROM transaction WHERE m_id = ? AND status = 'borrowed'";
            prep = con.prepareStatement(borrowed_book_Query);
            prep.setInt(1, m_id);
            res = prep.executeQuery();

            if (!res.next()) {
                System.out.println("No Book borrowed found!");
                return;
            }
            System.out.println("Borrowed Books:");
            do{
                       System.out.println("Transaction ID: " + res.getInt("t_id") + ", Book ID: " + res.getInt("b_id"));
                    } while (res.next()) ;
                // Ask for the transaction ID to return
            System.out.print("Enter the Transaction ID of the book you want to return: ");
            int transactionId = input.nextInt();
            input.nextLine(); // error so  newline

            // Check if the transaction exists
            String transactionCheckQuery = "SELECT * FROM transaction WHERE t_id = ? AND m_id = ?";
            prep = con.prepareStatement(transactionCheckQuery);
            prep.setInt(1, transactionId);
            prep.setInt(2, m_id);
            res = prep.executeQuery();
            res.next();
            // Get book ID and number of copies borrowed
            int b_id = res.getInt("b_id");
            String status = res.getString("status");

            // Update the transaction to set return date and change status to 'returned'
            String updateTransaction = "UPDATE transaction SET return_date = CURDATE(), status = 'returned' WHERE t_id = ?";
            prep = con.prepareStatement(updateTransaction);
            prep.setInt(1, transactionId);
            prep.executeUpdate();

            // Update available copies in the book table
            String updateBook = "UPDATE book SET available_copies = available_copies + 1 WHERE b_id = ?";
            prep = con.prepareStatement(updateBook);
            prep.setInt(1, b_id);
            prep.executeUpdate();

            System.out.println("Book returned successfully!");
                

    if (!res.next()) {
        System.out.println("Transaction not found!");
        return;
    }    
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    void queryTran(Connection con, Scanner input) {
    try {
        System.out.print("Enter 1 to query by Member Name or 2 to query by Book Title: ");
        int choice = input.nextInt();
        input.nextLine(); // Consume newline

        String searchField = (choice == 1) ? "m.name" : (choice == 2) ? "b.title" : null;
        if (searchField == null) {
            System.out.println("Invalid choice");
            return;
        }

        System.out.print("Enter "+searchField+" name:");
        String searchTerm = input.nextLine();

        String query = "SELECT t.t_id, m.name, b.title, t.borrow_date, t.return_date, t.status " +
                       "FROM transaction t " +
                       "JOIN member m ON t.m_id = m.m_id " +
                       "JOIN book b ON t.b_id = b.b_id " +
                       "WHERE " + searchField + " LIKE ?";

        PreparedStatement prep = con.prepareStatement(query);
        prep.setString(1,"%"+searchTerm+"%"); // Use LIKE for search term

        ResultSet res = prep.executeQuery();

        if (!res.next()) {
            System.out.println("No transactions found for the specified criteria.");
            return;
        }

        System.out.println("Transaction Details:");
        do {System.out.println("-----------------------------");
            System.out.println("Transaction ID: " + res.getInt("t_id"));
            System.out.println("Member Name: " + res.getString("name"));
            System.out.println("Book Title: " + res.getString("title"));
            System.out.println("Borrow Date: " + res.getDate("borrow_date"));
            System.out.println("Return Date: " + res.getDate("return_date"));
            System.out.println("Status: " + res.getString("status"));
            System.out.println("-----------------------------");
        } while (res.next());

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
