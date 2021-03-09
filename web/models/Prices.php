<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "prices".
 *
 * @property int $id
 * @property string $donate
 * @property int $priceInRubles
 * @property int $priceInGame
 */
class Prices extends \yii\db\ActiveRecord
{
    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'prices';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['donate', 'priceInRubles', 'priceInGame'], 'required'],
            [['donate'], 'string'],
            [['priceInRubles', 'priceInGame'], 'integer'],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'donate' => 'Donate',
            'priceInRubles' => 'Price In Rubles',
            'priceInGame' => 'Price In Game',
        ];
    }
}
