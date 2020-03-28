package com.android.quizapp;

import android.content.Context;
import android.content.Intent;

class AppNavigator {


    static void navigateFromTo(Context from, Class to) {
        Intent intent = new Intent(from, to);
        from.startActivity(intent);
        from.startActivity(intent);
    }
}
