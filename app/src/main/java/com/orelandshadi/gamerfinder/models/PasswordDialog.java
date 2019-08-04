package com.orelandshadi.gamerfinder.models;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

public class PasswordDialog extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("INFORMATION PASS")
                .setMessage("# a digit must occur at least once\n" +
                        "# a lower case letter must occur at least once\n" +
                        "# an upper case letter must occur at least once\n" +
                        "# a special character must occur at least once you can replace with your special characters\n" +
                        "# no whitespace allowed in the entire string\n" +
                        "# anything, at least six places though")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        return builder.create();
    }
}
