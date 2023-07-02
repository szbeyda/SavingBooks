
### Save Your Books 
 Uygulama kullanıcının Kayıt olarak okuduğu kitapları eklemesi ve bunları liste haline getirebilmesini baz alır.
 Öncelikle kullanıcı Sign Up üzerinde üye kaydı yaptıktan sonra Log In Üzerinden üye girişi yapabilir. Kullanıcı Kullanıcı adı ve şifreyi yanlış girme durumuzda "You entered the wrong username or password" uyarı mesajı verilir. Doğru Girişten sonra kullanıcı uygulamaya yönlendirilir. Kullanıcı giriş bilgileri User sayfasında yer almaktadır. Başlıngaçta Kitap kaydı bulunmayanın kullanıcının User sayfasındaki Book Count sayacı 0 olarak gösterilir. Add Book Sayfasından kullanıcı Kitap Fotoğrafı, Kitap Adı, Yazar Adı ve Özet bilgilerini girer ve kitabı kaydeder. Güncel kitap listesi Book List sayfasında Liste olarak verilir. Kullanıcı kitap eklemesi yaptıkça Book Counter Güncellenir. Kullanıcı Book List sayfasında eklediği kitap üzerinde arama yapabilir.
 LogIn, SıgnUp ve 3 ayrı Fragmente bağlı (BookList-AddBook-User) 3 MainActivity sayfası kullanılmıştır.
 Kullanıcı giriş bilgileri ve Kullanıcnın girdiği kitap bilgileri, veritabanı için Data Acces Object(DAO) kullanılmıştır. (UserDao-BookDao) 
 
 Add Book Sayfası : 
 - Kitap fotoğrafı için :bookImageView üzerinde bir OnClickListener atanır. Bu resme tıklandığında, galeriye erişmek için openGallery yöntemi çağrılır. openGallery metodu, galeriye erişim sağlamak için bir intent oluşturur ve startActivityForResult ile başlatır.
 - Kitao bilgileri kaydı için : saveButton üzerinde bir OnClickListener atanır. Bu düğme tıklandığında, EditText alanlarındaki metinleri alır, seçilen resim URI'sini alır, yeni bir Book nesnesi oluşturur ve veritabanına ekler. Ardından EditText alanları temizlenir ve seçilen resim gösterilir.
   
Book List Sayfası :
- Kitap Sayacı : onViewCreated metodu: Fragment görünümü oluşturulduğunda çağrılır. RecyclerView'i yapılandırır, veritabanından kitap listesini alır ve filtrelenmiş kitap listesiyle birlikte adaptörü ayarlar. Ayrıca, arama kutusuna metin girildiğinde tetiklenecek bir TextWatcher ekler. UpdateBookCount metodu: Kitap sayısını güncellemek için kullanılır. Adapter null değilse, adapter'i güncelleyerek sayaç değerini yansıtır.
- Kitap Filtreleme : filter metodu: Verilen metne göre kitapları filtreler. Eğer metin boş ise, tüm kitapları ekler. Aksi halde, kitap listesinde metni içeren kitapları ekler. Son olarak, adaptördeki kitap listesini günceller ve adaptörü yeniden yükler.
- BookListAdapter RecyclerView adaptörü sınıfını içerir.
  - BookListAdapter constructor'ı: BookListAdapter sınıfını başlatırken, kitap listesini alır ve books değişkenine atar.
  - onCreateViewHolder metodu: Yeni bir ViewHolder oluşturur. İlgili layout dosyasını (item_book) inflate eder ve ViewHolder'ı döndürür.
  - onBindViewHolder metodu: ViewHolder'a bağlı olan kitap verilerini görüntüler. Kitap adını, yazarını, özetini ve resmini (varsa) görüntüler. Resim yolu mevcutsa, Glide kütüphanesini kullanarak resmi yükler
  - ViewHolder sınıfı: RecyclerView içindeki her bir öğe için görünüm öğelerini tutar. bookImage, bookName, bookWriter ve bookSummary gibi görünüm öğelerine referans verir.
    
User Sayfası :
- onCreateView metodu: Fragment'in oluşturulduğu görünümü inflate eder. İlgili layout dosyasını (fragment_person) inflate eder ve görünüm öğelerine referanslar alır.
- Kişisel bilgilerin görüntülenmesi: ApplicationDatabase sınıfını kullanarak veritabanına erişir ve kullanıcının kişisel bilgilerini alır. Alınan bilgileri TextView öğelerine yerleştirir.
- Kitap sayısının görüntülenmesi: BookListAdapter sınıfını kullanarak kitap sayısını alır ve bookCountTextView'de görüntüler.
- Çıkış yapma işlemi: btnexit butonuna bir tıklama olayı dinleyicisi ekler. Tıklandığında MainActivityLogin sınıfına yönlendirme yapar ve çıkış yapma işlemini gerçekleştirir.
 
 Uygulama Video Linki:

https://youtu.be/fTzedNRhaOw

Uygulama Ekran Görüntüleri:
<div align="center">
  <img src="https://www.linkpicture.com/q/Login_1.jpeg" width=250>
 <img src="https://www.linkpicture.com/q/Signup.jpeg" width=250>
 <img src="https://www.linkpicture.com/q/Book-List.jpeg" width=250>
 <img src="https://www.linkpicture.com/q/Add-Book.jpeg" width=250>
 <img src="https://www.linkpicture.com/q/User.jpeg" width=250>
<div/>
 
