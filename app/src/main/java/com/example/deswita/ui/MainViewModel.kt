package com.example.deswita.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.deswita.models.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import com.example.deswita.utils.*

class MainViewModel : ViewModel() {

    private var _date = MutableLiveData<String>()
    val date: LiveData<String> get() = _date

    private var _weather = MutableLiveData<weatherResponse>()
    val weather: LiveData<weatherResponse> get() = _weather

    var username: String = "admin"
    var password: String = "admin"

    init {
        setDate(Date())
    }

    fun setWeather(weather: weatherResponse) {
        _weather.postValue(weather)
    }

    fun setDate(date: Date) {
        val sdf = SimpleDateFormat("dd MMMM yyyy")
        val currentDate = sdf.format(date)
        _date.postValue(currentDate)
    }

    fun getDate(): Long {
        val sdf = SimpleDateFormat("dd MMMM yyyy").parse(date.value)
        return sdf.time
    }

    var comments = listOf<Comment>(
        Comment(
            "Rudi-ansykah",
            "Om ded… undang Ericko Lim bahas youtube rewind dong.., sepertinya kemarin keras banget sindirannya.",
            "12 jan 2022"
        ),
        Comment(
            "Nurdin alayis",
            "Masih anget, langsung gaasss om ded \uD83D\uDE0D\uD83D\uDE0D\uD83D\uDC4D\uD83C\uDFFB\uD83D\uDC4D\uD83C\uDFFB",
            "12 jan 2022"
        ),
        Comment(
            "Dedi corbuzier",
            "om tolong undang alip ba ta, belau gitaris yang skillnya bisa dibilang professional dan sering di react sama ytber gitar professional dari luar negeri tp beliau jarang ke up di publik",
            "12 jan 2022"
        ),
    )

    var storiesDummy = arrayListOf<Story>(
        Story(
            id = 1,
            name = "Fiqri ardiansyah",
            description = "Jakarta ,indonesia",
            contentText = "kota Medan ini merupakan salah satu gedung dengan spot ",
            profile = "user_1",
            commentTotal = 3,
            contentImage = "post_4",
            likeTotal = 3,
            comments = comments
        ),
        Story(
            id = 2,
            name = "Farhan ismul A.",
            description = "Medan ,indonesia",
            contentText = "Lorem Ipsum is simply dummy text of the printing",
            profile = "user_2",
            commentTotal = 5,
            contentImage = "post_3",
            likeTotal = 7,
            comments = comments
        ),
        Story(
            id = 3,
            name = "Rizky kurniawan",
            description = "Medan ,indonesia",
            contentText = "Lorem Ipsum is simply dummy text of the printing",
            profile = "user_3",
            commentTotal = 3,
            contentImage = "post_2",
            likeTotal = 10,
            comments = comments
        ),
        Story(
            id = 4,
            name = "Amat sobirin",
            description = "Bekasi ,indonesia",
            contentText = "Lorem Ipsum is simply dummy text of the printing",
            profile = "user_4",
            commentTotal = 2,
            contentImage = "post_1",
            likeTotal = 3,
            comments = comments
        ),
        Story(
            id = 5,
            name = "Imagin dragon",
            description = "Jayapuran ,indonesia",
            contentText = "Lorem Ipsum is simply dummy text of the printing",
            profile = "user_5",
            commentTotal = 1,
            contentImage = "post_5",
            likeTotal = 5,
            comments = comments
        ),
        Story(
            id = 6,
            name = "Sule S.",
            description = "Jakarta ,indonesia",
            contentText = "Lorem Ipsum is simply dummy text of the printing",
            profile = "user_6",
            commentTotal = 0,
            contentImage = "post_6",
            likeTotal = 2,
            comments = comments
        )
    )

    var storiesDummy2 = arrayListOf<Story>(
        Story(
            id = 1,
            name = "Fiqri ardiansyah",
            description = "Jakarta ,indonesia",
            contentText = "kota Medan ini merupakan salah satu gedung dengan spot ",
            profile = "user_1",
            commentTotal = 3,
            contentImage = "post_4",
            likeTotal = 3,
            comments = comments
        ),
        Story(
            id = 2,
            name = "Fiqri ardiansyah",
            description = "Jakarta ,indonesia",
            contentText = "Tidak hanya itu, jalur setapaknya pun dihiasi dengan payung gantung yang kerap dijadikan spot favorit",
            profile = "user_1",
            commentTotal = 3,
            contentImage = "post_7",
            likeTotal = 3,
            comments = comments
        ),
        Story(
            id = 3,
            name = "Fiqri ardiansyah",
            description = "Medan ,indonesia",
            contentText = "Jangan lupa berkunjung di taman Labirin saat anda berada di kota Medan.",
            profile = "user_1",
            commentTotal = 3,
            contentImage = "post_8",
            likeTotal = 3,
            comments = comments
        ),
    )

    fun getStory(): List<Story> {
        val rand = (0..3).random()
        val list = listOf(storiesDummy,storiesDummy2)
        return list[rand]
    }


    var eventDummy1 = arrayListOf<Event>(
        Event(
            id = 1,
            image = "ev_1",
            date= "20 february 2022",
            name = "Taman Mora Indah",
            location = "Jl. Sisingamangaraja, Bangun Mulia, Kec. Medan Amplas, Kota Medan, Sumatera Utara.",
            description = "Dengan konsep waterpark, berbagai wahana air pun tersedia. Anda bisa menikmati segarnya air dengan berenang,"
        ),
        Event(
            id = 2,
            image = "ev_2",
            date= "20 february 2022",
            name = "Danau Toba",
            location = "Bukit Barisan, Kabupaten Toba Samosir, Sumatera Utara.",
            description = "Hampir setiap hari, wisata Danau Toba ini selalu ramai dipadati oleh pengunjung baik wisatawan lokal maupun wisatawan asing."
        ),
        Event(
            id = 3,
            image = "ev_3",
            date= "20 february 2022",
            name = "Menara Pandang Tele",
            location = "Turpuk Limbong, Kec. Harian, Kab. Samosir, Sumatra Utara.",
            description = "Menara Pandang Tele tidak kalah ramainya dengan wisata Danau Toba karena melalui wisata Menara ini"
        ),
        Event(
            id = 4,
            image = "ev_4",
            date= "20 february 2022",
            name = "Geosite Sipinsur",
            location = "Parulohan, Kec. Paranginan, Kab. Humbang Hasundutan, Sumatra Utara.",
            description = "Dari tempat wisata Sipinsur ini anda dapat menikmati berbagai pemandangan berupa hutan, bukit dan bahkan danau Toba."
        ),
        Event(
            id = 5,
            image = "ev_5",
            date= "20 february 2022",
            name = "Danau Linting",
            location = "Jl. Sinembah Tj. Kec. Muda Hulu, Kab. Deli Serdang, Sumatera Utara.",
            description = "Kesan instagenik juga tidak terlepas dari keunikan warna air danau yang kerap berubah."
        ),
    )

    var eventDummy2 = arrayListOf<Event>(
        Event(
            id = 6,
            image = "ev_6",
            date= "1 january 2022",
            name = "Istana Maimun",
            location = "Jl. Sultan Ma’moen Al Rasyid",
            description = "Istana Maimun merupakan salah satu objek wisata ikonik di kota Medan."
        ),
        Event(
            id = 7,
            image = "ev_7",
            date= "1 january 2022",
            name = "Danau Siombak",
            location = "Jl. Pasar Nippon Ujung Kel, Paya Pasir,",
            description = "Perlu diketahui bahwa danau Siombak ini merupakan salah satu danau buatan yang terletak diantara sungai Deli"
        ),
        Event(
            id = 8,
            image = "ev_8",
            date= "1 january 2022",
            name = "Maha Vihara Adhi Maitreya",
            location = "Jl. Cemara Asri Boulevard Raya No.Utara, Medan Estate",
            description = "kota Medan juga memiliki wisata religi yang dapat anda kunjungi. "
        ),
        Event(
            id = 9,
            image = "ev_12",
            date= "1 january 2022",
            name = "Kolam Renang Seksama Indah",
            location = "Jl. M. Nawi Harahap No.125, Sitirejo III, Kec. Medan Amplas",
            description = "Krensi adalah salah satu objek wisata air yang cukup terkenal di Medan."
        ),
        Event(
            id = 10,
            image = "ev_10",
            date= "1 january 2022",
            name = "Tjong A Fie Mansion",
            location = "Jl. Jend. Ahmad Yani No.105, Kesawan, Kec. Medan Barat, Kota Medan, Sumatera Utara.",
            description = "Dengan bangunan bergaya Eropa dan Chinesse membuatnya semakin ramai didatangi"
        ),
    )

    var eventDummy3 = arrayListOf<Event>(
        Event(
            id = 11,
            image = "ev_11",
            date= "2 january 2022",
            name = "Masjid Raya Medan",
            location = "Jl. Sisingamangaraja No.61, Mesjid, Kec. Medan Kota, Kota Medan, Sumatra Utara.",
            description = "Maka tak heran jika sampai saat ini masjid Raya Medan yang merupakan salah satu masjid terbesar"
        ),
        Event(
            id = 12,
            image = "ev_12",
            date= "2 january 2022",
            name = "Graha Maria Annai",
            location = " Jl. Sakura III No.7-10, Tj. Selamat, Kec. Medan Tuntungan",
            description = "Tempat wisata ini sangatlah ramai dikunjungi oleh wisatawan."
        ),
        Event(
            id = 13,
            image = "ev_13",
            date= "2 january 2022",
            name = "Rahmat Internasional Wildlife Museum",
            location = "Jl. S. Parman No.309, Petisah Hulu, Kec. Medan Baru, Kota Medan, Sumatera Utara.",
            description = "museum ini sebagai salah satu destinasi wisata yang menarik di kota Medan."
        ),
        Event(
            id = 14,
            image = "ev_14",
            date= "2 january 2022",
            name = "Tjong Yong Hian",
            location = "Jl. Kejaksaan, Kec. Medan Petisah, Kota Medan, Sumatera Utara.",
            description = "sentuhan arsitektur china jaman lawas akan hadir begitu melewati gerbang masuk"
        ),
    )

    var eventDummy4 = arrayListOf<Event>(
        Event(
            id = 15,
            image = "ev_15",
            date= "3 january 2022",
            name = "Taman Labirin",
            location = "Kab. Karo, Sumatera Utara.",
            description = "Jangan lupa berkunjung di taman Labirin saat anda berada di kota Medan."
        ),
        Event(
            id = 16,
            image = "ev_16",
            date= "3 january 2022",
            name = "Funland Mikie Holiday",
            location = "Jl. Jamin Ginting, Sempajaya, Kec. Berastagi",
            description = "namun semua akan sebanding dengan seluruh fasilitas yang disediakan ditempat ini."
        ),
        Event(
            id = 17,
            image = "ev_17",
            date= "3 january 2022",
            name = "Gedung London",
            location = "Jl. Jend. Ahmad Yani No.2",
            description = "Gedung yang terletak di jalan Kesawen kota Medan"
        ),
        Event(
            id = 18,
            image = "ev_18",
            date= "3 january 2022",
            name = "Sipisopiso",
            location = "Tongging, Kec. Merek, Kab. Karo, Sumatera Utara.",
            description = "anda dapat menikmati pemandangan dari danau Toba."
        ),
    )

    fun getEvent(): List<Event> {
        val rand = (0..3).random()
        val list = listOf(eventDummy1,eventDummy2,eventDummy3,eventDummy4)
        return list[rand]
    }


    var destinationDummy1 = arrayListOf<Destination>(
        Destination(
            1,
            "des_1",
            "Danau Toba",
            "Bukit Barisan, Kabupaten Toba Samosir, Sumatera Utara.",
            false,
            3.5,
            2323.2
        ),
        Destination(
            2,
            "des_2",
            "Hairos",
            "Jl. Jamin Ginting, Namo Simpur, Kec. Pancur Batu, Kab. Deli Serdang, Sumatera Utara.",
            false,
            4.0,
            2323.2
        ),
        Destination(
            3,
            "des_3",
            "Penangkaran Buaya",
            "Jl. Bunga Raya II",
            true,
            4.5,
            123.2
        ),
        Destination(
            4,
            "des_4",
            "Arung Jeram Sungai Bingei",
            "Jl. Setia Budi No.132, Tj. Sari",
            true,
            5.0,
            3432.2
        ),
        Destination(
            5,
            "des_5",
            "Wondrs Waeter World",
            "Jl. Padang Golf CBD Polonia, Suka Damai",
            false,
            3.0,
            232.2
        ),
    )

    var destinationDummy2 = arrayListOf<Destination>(
        Destination(
            6,
            "des_6",
            "The Le Hu Garden",
            "Jl. Pendidikan, Deli Tua Bar",
            false,
            3.5,
            2323.2
        ),
        Destination(
            7,
            "des_7",
            "Cagar Alam Sibolangit",
            "Jl. Desa Sibolangit, Kec. Sibolangit",
            false,
            4.5,
            122.2
        ),
        Destination(
            8,
            "des_8",
            "Pantai Mangrove",
            "Sei Naga Lawan, Kec. Perbaungan",
            false,
            2.5,
            122.2
        ),
        Destination(
            9,
            "des_9",
            "Merdeka Walk Medan",
            "Jl. Balai Kota, Kesawan, Kec. Medan Barat",
            false,
            4.5,
            2132.2
        ),
        Destination(
            10,
            "des_10",
            "Merdeka Walk Medan",
            "Jl. Balai Kota, Kesawan, Kec. Medan Barat",
            false,
            4.5,
            2132.2
        ),
    )

    var destinationDummy3 = arrayListOf<Destination>(
        Destination(
            11,
            "des_11",
            "Merci Barn",
            "Jl. Pahlawan Deli Tua- Medan",
            false,
            4.5,
            2323.2
        ),
        Destination(
            12,
            "des_12",
            "Kampung Ladang Outbuond",
            "Jl. Tuntungan, Namorih, Pancur Batu, Namorih",
            false,
            4.5,
            2323.2
        ),
        Destination(
            13,
            "des_13",
            "Kampung Keling",
            "Jl. Teuku Cik Ditiro, Madras Hulu,Kec. Medan Polonia",
            false,
            4.0,
            2323.2
        ),
        Destination(
            14,
            "des_14",
            "Air Terjun Dua Warna",
            "Bandar Baru, Sibolangit, Kab. Deli Serdang, Sumatera Utara.",
            false,
            4.5,
            232.2
        ),
        Destination(
            15,
            "des_15",
            "The Garden Café",
            "Jl. Multatuli Blok AA No.1-5, Hamdan, Kec. Medan Maimun",
            false,
            4.5,
            454.2
        ),
    )

    var destinationDummy4 = arrayListOf<Destination>(
        Destination(
            16,
            "des_16",
            "Hillpark Sibolangit",
            "Jl. Letjend Jamin Ginting , Suka Makmur",
            false,
            4.5,
            232.2
        ),
        Destination(
            17,
            "des_17",
            "Resep Nenek Moyangku",
            "Jl. Teuku Umar No.3e, Petisah Tengah",
            false,
            4.5,
            234.2
        ),
        Destination(
            18,
            "des_18",
            "Upside Down World",
            "Jl. K.H. Wahid Hasyim No.29",
            false,
            4.0,
            234.2
        ),
        Destination(
            19,
            "des_19",
            "Royal Sumatera",
            "Mangga, Kec. Medan Tuntungan, Kota Medan",
            false,
            4.0,
            234.2
        ),
        Destination(
            20,
            "des_20",
            "Wisata Sawah Pematang Johar",
            "Jl. Pematang Johar, Kec. Labuhan Deli, Kab. Deli Serdang",
            false,
            4.0,
            455.2
        ),
    )

    fun getDestination(): List<Destination> {
        val rand = (0..3).random()
        val list = listOf(destinationDummy1,destinationDummy2,destinationDummy3,destinationDummy4)
        return list[rand]
    }


    val reviewDummy1 = arrayListOf<Review>(
        Review(
            "Fiqri ardians",
            "user_1",
            "1 minggu lalu",
            "Tempat bersejarah, belajar sejarah, salah satu icon sejarah suku tionghoa datang ke kota medan pada zaman kesultanan Deli Medan",
            3.5f,
            1,
            1
        ),
        Review(
            "Farhan ismul",
            "user_3",
            "1 hari lalu",
            "Di dalamnya anda akan menikmati pemandangan taman yang sangat cantik",
            4.5f,
            1,
            1
        ),
        Review(
            "Rizky kurniawan",
            "user_4",
            "3 hari lalu",
            "Meskipun perjalanan tempuh harus dilalui dengan sangat lama",
            4.5f,
            1,
            1
        ),
        Review(
            "Nona muda",
            "user_2",
            "3 hari lalu",
            "seolah – olah anda akan merasa sedang berada di London.",
            4.5f,
            1,
            1
        ),
        Review(
            "Rafael 12",
            "user_5",
            "3 hari lalu",
            "Dengan pengelolaan yang tepat, lokasi persawahan pun bisa menjadi objek wisata yang menarik animo warga",
            4.5f,
            1,
            1
        ),
    )
    val reviewDummy2 = arrayListOf<Review>(
        Review(
            "Jhony margue",
            "user_7",
            "1 minggu lalu",
            "Seperti di Wisata Sawah Pematang Johar",
            4.0f,
            1,
            1
        ),
        Review(
            "Rumanah_23",
            "user_8",
            "1 minggu lalu",
            "Tidak hanya itu, jalur setapaknya pun dihiasi dengan payung gantung yang kerap dijadikan spot",
            4.0f,
            1,
            1
        ),
        Review(
            "Robinrobin",
            "user_9",
            "1 minggu lalu",
            "Destinasi menarik di kota Medan yang masih sangat alami adalah Sipisopiso",
            4.0f,
            1,
            1
        ),
        Review(
            "Robinrobin",
            "user_10",
            "2 minggu lalu",
            "Tak sedikit wisatawan lokal dan asing yang mengunjungi tempat bersejarah ini",
            4.5f,
            1,
            1
        ),
        Review(
            "Dodi_j12",
            "user_6",
            "1 minggu lalu",
            "Tak hanya itu, masih banyak fasilitas lain yang disediakan.",
            4.5f,
            1,
            1
        ),
    )

    fun getReview(): List<Review> {
        val rand = (0..3).random()
        val list = listOf(reviewDummy1,reviewDummy2)
        return list[rand]
    }


    val galleryDummy1 = arrayListOf<String>("des_1","des_2","des_4","des_3","des_5")
    val galleryDummy2 = arrayListOf<String>("des_6","des_9","des_7","des_8","des_10")
    val galleryDummy3 = arrayListOf<String>("des_11","des_12","des_14","des_13","des_15")
    val galleryDummy4 = arrayListOf<String>("des_16","des_17","des_19","post_1","post_2")

    fun getGallery(): List<String> {
        val rand = (0..3).random()
        val list = listOf(galleryDummy1,galleryDummy2,galleryDummy3,galleryDummy4)
        return list[rand]
    }
}