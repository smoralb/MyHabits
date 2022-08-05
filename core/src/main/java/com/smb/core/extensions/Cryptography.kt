package com.smb.core.extensions

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties.*
import java.nio.charset.Charset
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

private const val ANDROID_KEYSTORE = "AndroidKeyStore"
private const val KEY_SIZE = 128
private const val ENCRYPTION_BLOCK_MODE = BLOCK_MODE_GCM
private const val SECRET_KEY_NAME = "Y0UR$3CR3TK3YN@M3"
private const val ENCRYPTION_PADDING = ENCRYPTION_PADDING_NONE
private const val ENCRYPTION_ALGORITHM = KEY_ALGORITHM_AES
private const val UTF_8 = "UTF-8"
private const val CIPHER_TRANSFORMATION =
    "$ENCRYPTION_ALGORITHM/$ENCRYPTION_BLOCK_MODE/$ENCRYPTION_PADDING"

val cipher: Cipher
    get() {
        return Cipher.getInstance(CIPHER_TRANSFORMATION)
    }

fun String.getSecretKey(): SecretKey {
    KeyStore.getInstance(ANDROID_KEYSTORE).apply {
        load(null)
        getKey(this@getSecretKey, null)?.let {
            return it as SecretKey
        }
    }
    return this.createSecretKey()
}

fun String.createSecretKey(): SecretKey {
    val paramsBuilder = KeyGenParameterSpec.Builder(
        this,
        PURPOSE_ENCRYPT or PURPOSE_DECRYPT
    ).apply {
        setBlockModes(ENCRYPTION_BLOCK_MODE)
        setEncryptionPaddings(ENCRYPTION_PADDING)
        setKeySize(KEY_SIZE)
        setUserAuthenticationRequired(true)
    }.build()

    val keyGenerator = KeyGenerator.getInstance(
        KEY_ALGORITHM_AES,
        ANDROID_KEYSTORE
    )

    keyGenerator.init(paramsBuilder)

    return keyGenerator.generateKey()
}

fun String.encrypt(cipher: Cipher): EncryptedData {
    val cipherText = cipher.doFinal(this.toByteArray(Charset.forName(UTF_8)))
    return EncryptedData(cipherText, cipher.iv)
}

fun ByteArray.decrypt(cipher: Cipher): String {
    val plainText = cipher.doFinal(this)
    return String(plainText, Charset.forName(UTF_8))
}

//data class EncryptedData(val ciphertext: ByteArray, val initializationVector: ByteArray)

class Cryptography {
    fun initCipherForEncryption(): Cipher {
        cipher.init(
            Cipher.ENCRYPT_MODE,
            SECRET_KEY_NAME.getSecretKey()
        )
        return cipher
    }

    fun initCipherForDecryption(): Cipher {
        cipher.init(
            Cipher.DECRYPT_MODE,
            SECRET_KEY_NAME.getSecretKey()
        )
        return cipher
    }
}