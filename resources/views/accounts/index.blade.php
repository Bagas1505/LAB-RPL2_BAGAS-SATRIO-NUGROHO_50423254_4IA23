<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Daftar Akun - accounTrace</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f8f9fa; }
        h2 { color: #333; }
        table { width: 100%; border-collapse: collapse; background: white; margin-top: 20px; }
        th, td { padding: 12px; border: 1px solid #ddd; text-align: left; }
        th { background-color: #007bff; color: white; }
    </style>
</head>
<body>
    <h2>Daftar Akun accounTrace (Sinkronisasi Java & Laravel)</h2>
    <table>
        <thead>
            <tr>
                <th>Kode Akun</th>
                <th>Nama Akun</th>
                <th>Saldo</th>
            </tr>
        </thead>
        <tbody>
            @foreach($accounts as $acc)
            <tr>
                <td>{{ $acc->account_code }}</td>
                <td>{{ $acc->account_name }}</td>
                <td>Rp {{ number_format($acc->balance, 2, ',', '.') }}</td>
            </tr>
            @endforeach
        </tbody>
    </table>
</body>
</html>