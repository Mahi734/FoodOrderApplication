btn.setOnClickListener{
    val i = Intent(this, Main2Activity::class.java)
    i.putExtra("key","value")
    startActivity(i)
}

//On second activity Class i get the data
{
    val name = intent.getStringExtra(KEY_1)

    textView.text = name
    StartActivity(i)
}
//Implecite intent
{
    val email = editText.text.toString()
    val i = Intent()
    i.action = Intent.ACTION_SENDTO
    i.data = Uri.parse("$email")
    //Intent.ACTION_VIEW
    //Intent.ACTION_DAIL
    i.putExtra(Intent.EXTRA_SUBJECT,"xyz")
    StartActivity(i)
}
//iNTENT FILTER
//WHAT KIND OF THING YOU ARE ACCPETING IN ACTIVITY
