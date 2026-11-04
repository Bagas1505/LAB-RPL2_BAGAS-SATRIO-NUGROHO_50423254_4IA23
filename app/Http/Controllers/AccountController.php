<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class AccountController extends Controller
{
    public function index()
    {
        // Mengambil data dari tabel accounts
        $accounts = DB::table('accounts')->get();

        // Mengirim data ke tampilan web (Blade)
        return view('accounts.index', compact('accounts'));
    }
}