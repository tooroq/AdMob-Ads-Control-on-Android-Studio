هذه الملفات ليست لتطبيق متكامل هي فقط للحصول على الاكواد الخاصة باعلان بانر واعلان بيني وجلب الكود او المعرف الاعلاني من خلال الانترنت .
يتم استخدام صفحة ويب لعرض البيانات من نوع json 

يتم استخدام مكتبة الاعلانت الخاصة بادموب ومكتبة volley داخل ملف build.gradle
    implementation("com.android.volley:volley:1.2.1")
    implementation("com.google.android.gms:play-services-ads:22.0.0")

 ولا تنسى اعطاء صلاحية الوصول الى الانترنت واضافة معرف التطبيق بملف AndroidManifest لكي لا يظهر لك خطأ أقناء تشغيل التطبيق 
    ....
        <uses-permission android:name="android.permission.INTERNET" />
    ....
    ....
        <meta-data
            android:name="com.google.android.gms.ads.APPLICATION_ID"
            android:value="ca-app-pub-3940256099942544~3347511713"/> <!-- استبدل مع معرف التطبيق الخاص بك -->
    كتابة الاكواد لااخرى لاظهار الاعلان البيني والبانر من خلال جلب الكود الاعلاني مودين داخل MainActivity.java و 
