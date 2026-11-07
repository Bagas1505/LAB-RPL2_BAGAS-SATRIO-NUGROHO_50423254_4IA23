<?php

use App\Http\Controllers\AccountController;

Route::get('/accounts', [AccountController::class, 'index']);