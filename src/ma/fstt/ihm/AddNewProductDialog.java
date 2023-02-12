package ma.fstt.ihm;


        import java.awt.Color;
        import java.awt.Font;
        import java.awt.Image;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.IOException;
        import java.io.InputStream;
        import java.nio.file.Files;
        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Date;
        import javax.swing.BorderFactory;
        import javax.swing.ImageIcon;
        import javax.swing.JButton;
        import javax.swing.JDialog;
        import javax.swing.JFileChooser;
        import javax.swing.JFrame;
        import javax.swing.JLabel;
        import javax.swing.JOptionPane;
        import javax.swing.JTextField;
        import javax.swing.filechooser.FileNameExtensionFilter;
        import javax.swing.table.DefaultTableModel;


// ليحصل على معلومات الإتصال بقاعدة البيانات DBInfo ينفذ الإنترفيس AddNewProductDialog هنا جعلنا الكلاس
// لأننا سنحدد بداخله ماذا يحدث عند النقر على الأزرار ActionListener و جعلناه أيضأ ينفذ الإنترفيس
public class AddNewProductDialog implements DBInfo, ActionListener {


    // هنا قمنا بتعريف النافذة المنبثقة و محتوياتها
    JDialog dialog;
    JLabel image, nameLabel, priceLabel;
    JTextField nameField, priceField;
    JButton chooseImageButton, addButton;
    String selectedImagePath = null;
    DefaultTableModel model;
    JFrame frame;


    // عند إنشاء كائن من هذا الكلاس, يجب تمرير النافذة التي سيظهر فيها و الجدول الذي سيتم إضافة المنتج الجديد فيه
    public AddNewProductDialog(JFrame frame, DefaultTableModel model)
    {
        // هنا قمنا بإنشاء النافذة المنبثقة و محتواها و ربطناها بنافذة البرنامج الأساسية و بجدول المنتجات
        dialog = new JDialog(frame);
        image = new JLabel("", JLabel.CENTER);
        chooseImageButton = new JButton("choose Image", new ImageIcon(this.getClass().getResource("/images/add-image.png")));
        nameLabel = new JLabel("Name");
        priceLabel = new JLabel("Price ( $ )");
        nameField = new JTextField();
        priceField = new JTextField();
        addButton = new JButton("Add Product", new ImageIcon(this.getClass().getResource("/images/add-product.png")));
        this.model = model;
        this.frame = frame;

        // هنا قمنا بتحديد حجم و مكان كل شيء سيتم إضافته في النافذة المنبثقة
        image.setBounds(36, 40, 224, 224);
        nameLabel.setBounds(300, 30, 80, 40);
        nameField.setBounds(300, 70, 270, 40);
        priceLabel.setBounds(300, 120, 80, 40);
        priceField.setBounds(300, 160, 270, 40);
        chooseImageButton.setBounds(298, 220, 274, 45);
        addButton.setBounds(34, 310, 538, 60);

        // هنا قمنا بتحديد خصائص الأشياء التي سيتم إضافتها في النافذة المنبثقة
        chooseImageButton.setFont(new Font("Arial", Font.BOLD, 15));
        addButton.setForeground(Color.white);
        addButton.setBackground(Color.black);
        addButton.setFont(new Font("Arial", Font.BOLD, 18));
        image.setBackground(Color.gray);
        image.setForeground(Color.white);
        image.setOpaque(true);
        image.setBorder(BorderFactory.createLineBorder(Color.gray, 1, true));
        image.setFont(new Font("Arial", Font.BOLD, 22));
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameField.setFont(new Font("Arial", Font.BOLD, 15));
        nameField.setBorder(BorderFactory.createLineBorder(Color.gray, 2, true));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        priceField.setFont(new Font("Arial", Font.BOLD, 15));
        priceField.setBorder(BorderFactory.createLineBorder(Color.gray, 2, true));

        // هنا قمنا بإضافة جميع الأشياء التي قمنا بإنشائها في النافذة المنبثقة
        dialog.add(image);
        dialog.add(chooseImageButton);
        dialog.add(nameLabel);
        dialog.add(priceLabel);
        dialog.add(nameField);
        dialog.add(priceField);
        dialog.add(addButton);

        // عند النقر على أي زر موجود في النافذة المنبثقة actionPerformed() هنا قلنا أنه سيتم إستدعاء الدالة
        chooseImageButton.addActionListener(this);
        addButton.addActionListener(this);

        // هنا قمنا بتحديد خصائص و مكان ظهور النافذة المنبثقة
        dialog.setLayout(null);
        dialog.setSize(630, 420);
        dialog.setTitle("Add New Product");
        dialog.setModal(false);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(frame);
    }


    // و التي سنستخدمها لإظهار النافذة المنبثقة show() هنا قمنا ببناء الدالة
    public void show() {
        nameField.setText("");
        priceField.setText("");
        image.setText("No image selected");
        image.setIcon(null);
        dialog.setVisible(true);
    }


    // و التي سنستخدمها لإخفاء النافذة المنبثقة hide() هنا قمنا ببناء الدالة
    public void hide() {
        dialog.setVisible(false);
    }


    // و التي سنستخدمها كلما أردنا الإتصال بقاعدة البيانات getConnection() هنا قمنا ببناء الدالة
    private Connection getConnection()
    {
        Connection con;

        try {
            // DBInfo معلومات الإتصال بقاعدة البيانات قمنا بجلبها من الإنترفيس
            con = DriverManager.getConnection(DBInfo.DB_NAME_WITH_ENCODING, DBInfo.USER, DBInfo.PASSWORD);
            return con;
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }


    // و التي سنقوم باستدعائها كلما تم إضافة منتج جديد لإظهاره في الجدول viewProductsInTheTable() هنا قمنا ببناء الدالة
    private void viewProductsInTheTable()
    {
        // لتخزين معلومات منتج واحد في كل عنصر فيه productقمنا بإنشاء المصفوفة
        ArrayList<ma.fstt.model.Product> productList= new ArrayList();

        // products هنا قمنا بالإتصال بقاعدة البيانات و بتجهيز الإستعلام الذي سيجلب جميع قيم الجدول
        Connection con = getConnection();
        String query = "SELECT * FROM products";

        // لتخزين نتيجة الإستعلام rs لتنفيذ الإستعلام, و الكائن st هنا قمنا بإنشاء الكائن
        Statement st;
        ResultSet rs;

        try {
            // rs هنا قمنا بتنفيذ الإستعلام و تخزين نتيجته في الكائن
            st = con.createStatement();
            rs = st.executeQuery(query);

            // في كل مرة rs لتخزين منتج واحد من المنتجات التي ستكون موجودة في الكائن product هنا قمنا بإنشاء الكائن
            ma.fstt.model.Product product1;

            // الحلقة التالية ترجع سطر واحد في كل مرة, أي معلومات منتج واحد
            while(rs.next())
            {
                // product بيانات المنتج التي سيتم إرجاعها في كل مرة سيتم تخزينها بشكل مؤقت في الكائن
                product1 = new ma.fstt.model.Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        Float.parseFloat(rs.getString("price")),
                        rs.getString("add_date"),
                        rs.getBytes("image")
                );
                // productكعنصر واحد في المصفوفة product في الأخير سيتم إضافة الكائن
                productList.add(product1);
            }

            // هنا قمنا بإغلاق الإتصال مع قاعدة البيانات لأننا لم نعد بحاجة إليها
            con.close();
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // هنا قمنا بمسح جميع أسطر الجدول
        model.setRowCount(0);

        // لتخزين سطر واحد في الجدول يتألف من 4 أعمدة في كل مرة row هنا قمنا بإنشاء المصفوفة
        Object[] row = new Object[4];

        // row في المصفوفة productهنا في كل مرة سيتم تخزين معلومات منتج واحد من المنتجات المخزنة في المصفوفة
        for(int i = 0; i<productList.size(); i++)
        {
            row[0] = productList.get(i).getId();
            row[1] = productList.get(i).getName();
            row[2] = productList.get(i).getPrice();
            row[3] = productList.get(i).getAddDate();

            // كسطر واحد في الجدول row بعدها سيتم إضافة عناصر المصفوفة
            model.addRow(row);
        }

    }


    // لفحص القيم التي أدخلها المستخدم في الحقول للتأكد من صحتها قبل إضافة المنتج في قاعدة البيانات checkInputs() قمنا ببناء الدالة
    private boolean checkInputs()
    {
        if( nameField.getText().equals("") && priceField.getText().equals("") ) {
            JOptionPane.showMessageDialog(dialog, "Name and Price fields cannot be empty !", "", JOptionPane.PLAIN_MESSAGE);
            return false;
        }

        else if(nameField.getText().equals("")) {
            JOptionPane.showMessageDialog(dialog, "Please enter product name", "", JOptionPane.PLAIN_MESSAGE);
            return false;
        }

        else if(priceField.getText().equals("")) {
            JOptionPane.showMessageDialog(dialog, "Please enter product price", "", JOptionPane.PLAIN_MESSAGE);
            return false;
        }

        try {
            Float.parseFloat(priceField.getText());
            return true;
        }
        catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(dialog,
                    "<html>"
                            + "<b>Price should be a decimal number.<br><br>"
                            + "Examples:<br>"
                            + "• 40<br>"
                            + "• 10.5</b>"
                            + "</html>",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }


    // لتعديل حجم أي صورة يختارها المستخدم للمنتج resizeImage() قمنا ببناء الدالة
    // لجعله يساوي حجم المكان المخصص لعرض الصورة التي تم إختيارها
    // تمثل الصورة التي يجب تعديل حجمها bytes عند إستدعائها يجب تمرير مصفوفة من الـ
    // no-image.jpg في حال لم يتم تمرير مصفوفة لها, سترجع صورة معدلة الحجم من الصورة الإفتراضية
    private ImageIcon resizeImage(byte[] pic)
    {
        // لحفظ الصورة التي سيتم تعديل حجمها myImage قمنا بتعريف الكائن
        ImageIcon myImage;

        // myImage في الكائن no-image.jpg سيتم تخزين الصورة pic في حال لم يتم تمرير مصفوفة تمثل صورة مكان البارامتير
        if(pic == null)
            myImage = new ImageIcon(this.getClass().getResource("/images/no-image.jpg"));

            // myImage تمثل صورة, سيتم تخزين هذه المصفوفة في الكائن bytes في حال تم تمرير مصفوفة من الـ
        else
            myImage = new ImageIcon(pic);

        // tempImage ثم قمنا بتخزينها بشكل مؤقت في الكائن myImage هنا قمنا بإنشاء صورة معدلة الحجم من الصورة المخزنة في الكائن
        Image tempImage = myImage.getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);

        // tempImage في الأخير قمنا بإرجاع الصورة المعدلة الحجم في الكائن
        return new ImageIcon(tempImage);
    }


    // لجعل المستخدم قادر على إختيار صورة موجودة في حاسوبه chooseImage() قمنا ببناء الدالة
    // Choose Image سيتم إستدعاء هذه الدالة عندما يقوم المستخدم بالنقر على الزر
    private void chooseImage()
    {
        // و الذي سيمثل نافذة منبثقة لإختيار صورة من الجهاز JFileChooser هنا قمنا بإنشاء كائن من الكلاس
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));

        // هنا قمنا بتحديد نوع الصور التي يمكنك للمستخدم إختيارها من جهازه
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Select a .JPG .PNG .GIF image", "jpg", "png", "gif");
        file.setFileFilter(filter);

        // هنا قمنا بإظهار النافذة و تخزين قيمة الزر الذي تم النقر عليه و أدى إلى إغلاق النافذة
        int result = file.showOpenDialog(dialog);

        // سيتم تعديل حجمها ليوافق حجم المربع, ثم ستوضع فيه Open بعد إختيار الصورة, إذا قام المستخدم بالنقر على الزر
        if(result == JFileChooser.APPROVE_OPTION) {
            try {
                byte[] selectedImage = Files.readAllBytes(file.getSelectedFile().toPath());
                image.setIcon(resizeImage(selectedImage));
                image.setText("");
                selectedImagePath = file.getSelectedFile().toPath().toString();
            }
            catch(IOException ex) {
                image.setIcon(resizeImage(null));
            }
        }
    }


    // لحفظ جميع المعلومات التي أدخلها المستخدم في الحقول إضافةً إلى الصورة التي إختارها في قاعدة البيانات insertProduct() قمنا ببناء الدالة
    private void insertProduct()
    {
        // إذا تم التشييك على الحقول و كان لا يوجد أي خطأ أو نقص في المعلومات المطلوب إدخالها
        if(checkInputs()) {
            try {
                // سيتم الإتصال مع قاعدة البيانات
                Connection con = getConnection();

                // ثم سيتم تجهيز الإستعلام الذي سيتم تنفيذه في ثاعدة البيانات لحفظ المعلومات المدخلة في الحقول
                PreparedStatement ps;

                // إذا لم يقم المستخدم بوضع صورة للمنتج, سيتم تخزين فقط المعلومات التي أدخلها في الحقول
                if (selectedImagePath == null) {
                    ps = con.prepareStatement("INSERT INTO products(name, price, add_date) values(?,?,?)");
                }

                // إذا قام المستخدم بوضع صورة للمنتج, سيتم تخزين المعلومات التي أدخلها في الحقول و الصورة التي إختارها أيضاً
                else {
                    ps = con.prepareStatement("INSERT INTO products(name, price, add_date, image) values(?,?,?,?)");

                    InputStream img = new FileInputStream(new File(selectedImagePath));
                    ps.setBlob(4, img);
                }

                ps.setString(1, nameField.getText());
                ps.setString(2, priceField.getText());

                // تاريخ إضافة المنتج سيتم تخزينه بشكل تلقائي
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String addDate = dateFormat.format(new Date());
                ps.setString(3, addDate);

                // في الأخير سيتم تنفيذ الإستعلام و إغلاق الإتصال مع قاعدة البيانات
                ps.executeUpdate();
                con.close();

                // بعدها سيتم إظهار المنتج الذي تم إضتفته في الجدول
                viewProductsInTheTable();

                // ثم سيتم مسح جميع المعلومات التي أدخلها المستخدم في الحقول, حتى يتمكن من إدخال معلومات منتج جديد بسرعة
                nameField.setText("");
                priceField.setText("");
                image.setText("No image selected");
                image.setIcon(null);
                selectedImagePath = null;
            }
            catch (FileNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }


    // هذه الدالة يتم إستدعاءها إذا تم النقر على أي زر موجود في النافذة المنبثقة
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // لفتح نافذة منبثقة تسمح بإختيار صورة للمنتج chooseImage() سيتم إستدعاء الدالة chooseImageButton إذا تم النقر على الزر
        if( e.getSource() == chooseImageButton)
            chooseImage();

            // لإضافة المنتج في قاعدة البيانات insertProduct() سيتم إستدعاء الدالة addButton إذا تم النقر على الزر
        else if(e.getSource() == addButton)
            insertProduct();
    }

}//  company_products_manager  موجود بداخل المجلد  Main.java  هنا ذكرنا أن الملف



