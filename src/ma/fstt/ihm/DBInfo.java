package ma.fstt.ihm;


public interface DBInfo {

        // هنا قمنا بتحديد إسم قاعدة البيانات و طريقة الوصول لقاعدة البيانات
        String DB_NAME = "jdbc:mysql://localhost:3306/GPharmacie";

        // هنا قمنا بتحديد نوع الترميز الذي سنستخدمه عند الإتصال بقاعدة البيانات. هذا الترميز يتيح لك تخزين البيانات باللغة العربية
        String ENCODING = "?useUnicode=yes&characterEncoding=UTF-8";

        // هنا قمنا بدمج إسم قاعدة البيانات و طريقة الوصول إليها و نوع الترميز في متغير واحد بهدف تقليل حجم الكود لاحقاً فقط
        String DB_NAME_WITH_ENCODING = DB_NAME + ENCODING;

        // هنا قمنا بتحديد إسم المستخدم في قاعدة البيانات
        String USER = "root";

        // هنا قمنا بتحديد كلمة مرور المستخدم في قاعدة البيانات
        String PASSWORD = "";
}

