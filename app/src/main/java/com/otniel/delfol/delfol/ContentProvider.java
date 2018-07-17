package com.otniel.delfol.delfol;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ContentProvider extends AppCompatActivity {
    SharedPref sharedpref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpref = new SharedPref(this);
        if(sharedpref.loadNightModeState()==true) {
            setTheme(R.style.darktheme);
        }
        else  setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        TextView contact = (TextView) findViewById(R.id.contact);
        Cursor cursor = getContacts();
        while (cursor.moveToNext()) {
/*Get contact information*/
            String contactID =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String displayName =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
            String displayPhone = "";
            int hasPhone =
                    cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
                    ;
/*Check if phone number exist*/
            if (hasPhone == 1) {
                Cursor phoneCursor =
                        getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =" + contactID, null, null);
                while (phoneCursor.moveToNext()) {
                    Log.v("Content Providers", displayPhone =
                            phoneCursor.getString(phoneCursor.getColumnIndex(
                                    ContactsContract.CommonDataKinds.Phone.NUMBER)));
                }
                phoneCursor.close();
            }
/*Display contact information*/
            contact.append(displayName + "\n" + displayPhone);
            contact.append("\n======================\n");
        }
    }
    private Cursor getContacts() {
/* Run query */
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String[] projection = new String[]{ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.HAS_PHONE_NUMBER};
        String selection = ContactsContract.Contacts.IN_VISIBLE_GROUP + " = '" +
                ("1") + "'";
        String[] selectionArgs = null;
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
        return managedQuery(uri, projection, selection, selectionArgs,
                sortOrder);
    }
}
