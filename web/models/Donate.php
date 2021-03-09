<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "donate".
 *
 * @property int $id
 * @property string $hwid
 * @property string $nick
 * @property string $donate
 */
class Donate extends \yii\db\ActiveRecord
{
    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'donate';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['hwid', 'nick', 'donate'], 'required'],
            [['hwid', 'nick', 'donate'], 'string'],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'hwid' => 'Hwid',
            'nick' => 'Nick',
            'donate' => 'Donate',
        ];
    }
}
