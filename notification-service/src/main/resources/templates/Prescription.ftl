<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<p>Hi ${user.firstName}</p>
<p>Below is your prescription details</p>
<#list medicineList as medicine>
    Medicine Name: ${medicine.name}
    Medicine Frequency: ${medicine.frequency}
    Medicine Dosage: ${medicine.dosage}
    Remarks: ${medicine.remarks}
</#list>
<p>Regards,</p>
<p>
    <em>${doctor.firstName}</em> <br />
</p>
</body>
</html>