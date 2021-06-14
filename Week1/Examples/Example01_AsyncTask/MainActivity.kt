import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.InputStream
import java.lang.ref.WeakReference
import java.net.URL
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var btnStartDownloading: Button
    private lateinit var btnStopDownloading: Button
    private lateinit var ivImage: ImageView
    private lateinit var pbProgress: ProgressBar
    private val downloadImageTask: DownloadTask = DownloadTask(this)

    private val imageURL = "http://i0.kym-cdn.com/entries/icons/mobile/000/013/564/doge.jpg"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnStartDownloading = findViewById(R.id.btnStartButton)
        btnStartDownloading.setOnClickListener {
            view -> downloadImageTask.execute(imageURL)
        }
        btnStopDownloading = findViewById(R.id.btnStopButton)
        btnStopDownloading.setOnClickListener {
            view -> downloadImageTask.cancel(true)
        }
        ivImage = findViewById(R.id.ivImage)
        pbProgress = findViewById(R.id.pgProgress)
    }

    companion object {
        class DownloadTask(activity: MainActivity) : AsyncTask<String, Unit, Bitmap>() {
            private var activityWeakReverence: WeakReference<MainActivity> = WeakReference(activity)

            override fun onPreExecute() {
                val activity = activityWeakReverence.get()
                activity?.pbProgress?.visibility = ProgressBar.VISIBLE
                activity?.btnStartDownloading?.isEnabled = false
                activity?.btnStopDownloading?.isEnabled = true
            }
            override fun doInBackground(vararg p0: String?): Bitmap {
                TimeUnit.SECONDS.sleep(5)
                val inputStream = URL(p0[0]).content as InputStream
                val d = BitmapFactory.decodeStream(inputStream)
                inputStream.close()
                return d
            }

            override fun onPostExecute(result: Bitmap?) {
                val activity = activityWeakReverence.get()
                activity?.pbProgress?.visibility = ProgressBar.GONE
                activity?.btnStopDownloading?.isEnabled = false
                activity?.ivImage?.setImageBitmap(result)
            }

            override fun onCancelled() {
                val activity = activityWeakReverence.get()
                activity?.pbProgress?.visibility = ProgressBar.GONE
                activity?.btnStopDownloading?.isEnabled = false
                Toast.makeText(activityWeakReverence.get(), "Downloading are stopped.",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}
